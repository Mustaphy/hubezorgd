package nl.hu.inno.delivery.core.application;

import nl.hu.inno.delivery.core.application.task.CompleteDelivery;
import nl.hu.inno.delivery.core.application.command.PostDeliveryReview;
import nl.hu.inno.delivery.core.application.command.ScheduleDelivery;
import nl.hu.inno.delivery.core.data.DeliveryEventPublisher;
import nl.hu.inno.delivery.core.data.storage.DeliveryRepository;
import nl.hu.inno.delivery.core.data.storage.DeliveryReviewRepository;
import nl.hu.inno.delivery.core.data.storage.RiderRepository;
import nl.hu.inno.delivery.core.domain.Delivery;
import nl.hu.inno.delivery.core.domain.DeliveryReview;
import nl.hu.inno.delivery.core.domain.ReviewRating;
import nl.hu.inno.delivery.core.domain.Rider;
import nl.hu.inno.delivery.core.domain.event.DeliveryEvent;
import nl.hu.inno.delivery.core.domain.exception.DeliveryNotFoundException;
import nl.hu.inno.delivery.core.domain.exception.NoAvailableRidersException;
import nl.hu.inno.delivery.core.domain.exception.DeliveryNotCompletedException;
import nl.hu.inno.delivery.core.domain.info.OrderInfo;
import nl.hu.inno.delivery.infrastructure.dto.DeliveryReviewDto;
import nl.hu.inno.delivery.infrastructure.gateway.HttpOrdersGateway;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

@Service
public class DeliveryCommandHandler {
    private final DeliveryRepository deliveryRepository;
    private final RiderRepository riderRepository;
    private final DeliveryReviewRepository deliveryReviewRepository;
    private final HttpOrdersGateway ordersGateway;
    private final ThreadPoolTaskScheduler taskScheduler;
    private final DeliveryEventPublisher eventPublisher;

    public DeliveryCommandHandler(DeliveryRepository deliveryRepository, RiderRepository riderRepository, DeliveryReviewRepository deliveryReviewRepository, HttpOrdersGateway ordersGateway, ThreadPoolTaskScheduler taskScheduler, DeliveryEventPublisher eventPublisher) {
        this.deliveryRepository = deliveryRepository;
        this.riderRepository = riderRepository;
        this.deliveryReviewRepository = deliveryReviewRepository;
        this.ordersGateway = ordersGateway;
        this.taskScheduler = taskScheduler;
        this.eventPublisher = eventPublisher;
    }

    public void handle(ScheduleDelivery command) {
        Rider rider = this.riderRepository
                .findAll()
                .stream()
                .min(Comparator.comparingInt(Rider::getNrOfDeliveries))
                .orElseThrow(() -> new NoAvailableRidersException("No available rider found."));

        Delivery delivery = Delivery.create(
                // Date which is between 20 and 30 seconds after current time
                LocalDateTime.now().plusSeconds(new Random().nextInt((30 - 20) + 1) + 20),
                rider,
                new OrderInfo(command.order(),command.username())
        );

        this.riderRepository.save(rider);
        this.publishEventsAndSave(delivery);

        // Schedule a task for 2 seconds after estimated delivery to complete delivery
        this.taskScheduler.schedule(
                new CompleteDelivery(this, delivery.getId()),
                Date.from(delivery.getEstimatedDelivery().plusSeconds(2).atZone(ZoneId.systemDefault()).toInstant())
        );
    }

    public void handle(CompleteDelivery task) {
        Delivery delivery = this.deliveryRepository
                .findById(task.id())
                .orElseThrow(() -> new DeliveryNotFoundException(String.format("Delivery with id '%s' could not be found", task.id())));

        if (delivery.isDelivered()) {
            delivery.deliver();
            this.publishEventsAndSave(delivery);
        }
    }

    public DeliveryReviewDto handle(PostDeliveryReview command) {
        Delivery delivery = this.deliveryRepository
                .findById(command.id())
                .orElseThrow(() -> new DeliveryNotFoundException(String.format("Delivery with id '%s' could not be found", command.id())));

        if (!this.ordersGateway.isDelivered(delivery.getOrder().id())) {
            throw new DeliveryNotCompletedException("You can't review a delivery that hasn't been delivered");
        }

        return DeliveryReviewDto.toDto(this.deliveryReviewRepository.save(new DeliveryReview(delivery, ReviewRating.fromInt(command.rating()), command.description(), command.user())));
    }

    private void publishEventsAndSave(Delivery delivery) {
        List<DeliveryEvent> events = delivery.listEvents();
        events.forEach(this.eventPublisher::publish);
        delivery.clearEvents();

        this.deliveryRepository.save(delivery);
    }
}

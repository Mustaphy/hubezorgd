package nl.hu.inno.delivery.core.application;

import nl.hu.inno.delivery.core.application.query.GetDeliveriesByUser;
import nl.hu.inno.delivery.core.application.query.GetDeliveryById;
import nl.hu.inno.delivery.core.application.query.GetDeliveryReviewsForDelivery;
import nl.hu.inno.delivery.core.data.storage.DeliveryRepository;
import nl.hu.inno.delivery.core.data.storage.DeliveryReviewRepository;
import nl.hu.inno.delivery.core.domain.Delivery;
import nl.hu.inno.delivery.core.domain.DeliveryReview;
import nl.hu.inno.delivery.core.domain.exception.DeliveryNotFoundException;
import nl.hu.inno.delivery.infrastructure.dto.DeliveryDto;
import nl.hu.inno.delivery.infrastructure.dto.DeliveryReviewDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliveryQueryHandler {
    private final DeliveryRepository deliveryRepository;
    private final DeliveryReviewRepository deliveryReviewRepository;

    public DeliveryQueryHandler(DeliveryRepository deliveryRepository, DeliveryReviewRepository deliveryReviewRepository) {
        this.deliveryRepository = deliveryRepository;
        this.deliveryReviewRepository = deliveryReviewRepository;
    }

    public List<DeliveryDto> handle(GetDeliveriesByUser query) {
        return this.deliveryRepository.findDeliveriesByOrder_Username(query.user().getUsername())
                .stream()
                .map(DeliveryDto::toDto)
                .toList();
    }

    public DeliveryDto handle(GetDeliveryById query) {
        return DeliveryDto.toDto(this.deliveryRepository.findById(query.id())
                .orElseThrow(() -> new DeliveryNotFoundException(String.format("Delivery with id '%s' could not be found.", query.id()))));
    }

    public List<DeliveryReviewDto> handle(GetDeliveryReviewsForDelivery query) {
        Delivery delivery = this.deliveryRepository.findById(query.id())
                .orElseThrow(() -> new DeliveryNotFoundException(String.format("Delivery with id '%s' could not be found.", query.id())));

        return this.deliveryReviewRepository.findByDelivery(delivery)
                .stream()
                .map(DeliveryReviewDto::toDto)
                .toList();
    }
}

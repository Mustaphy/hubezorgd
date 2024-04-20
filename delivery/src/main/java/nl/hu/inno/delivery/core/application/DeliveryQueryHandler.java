package nl.hu.inno.delivery.core.application;

import nl.hu.inno.delivery.core.application.query.GetDeliveries;
import nl.hu.inno.delivery.core.application.query.GetDeliveryById;
import nl.hu.inno.delivery.core.application.query.GetReviewsForDelivery;
import nl.hu.inno.delivery.core.data.storage.DeliveryRepository;
import nl.hu.inno.delivery.core.data.storage.DeliveryReviewRepository;
import nl.hu.inno.delivery.core.domain.Delivery;
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

    public List<DeliveryDto> handle(GetDeliveries query) {
        return this.deliveryRepository
                .findDeliveriesByOrder_Username(query.user().getUsername())
                .stream()
                .map(DeliveryDto::toDto)
                .toList();
    }

    public DeliveryDto handle(GetDeliveryById query) {
        return DeliveryDto.toDto(this.deliveryRepository
                .findDeliveryByDeliveryIdAndOrder_Username(query.id(), query.user().getUsername())
                .orElseThrow(() -> new DeliveryNotFoundException(String.format("Delivery with id '%s' could not be found.", query.id()))));
    }

    public List<DeliveryReviewDto> handle(GetReviewsForDelivery query) {
        Delivery delivery = this.deliveryRepository
                .findById(query.id())
                .orElseThrow(() -> new DeliveryNotFoundException(String.format("Delivery with id '%s' could not be found.", query.id())));

        return this.deliveryReviewRepository
                .findByDelivery(delivery)
                .stream()
                .map(DeliveryReviewDto::toDto)
                .toList();
    }
}

package nl.hu.inno.delivery.infrastructure.dto;

import nl.hu.inno.delivery.core.domain.Rider;

import java.util.List;
import java.util.UUID;

public record RiderDto(UUID id, String name, List<DeliveryDto> deliveries) {
    public static RiderDto toDto(Rider rider) {
        return new RiderDto(rider.getId(), rider.getName(), rider.getDeliveries().stream().map(DeliveryDto::toDto).toList());
    }
}

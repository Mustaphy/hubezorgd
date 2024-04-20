package nl.hu.inno.delivery.infrastructure.dto;

import nl.hu.inno.delivery.core.domain.Rider;

import java.util.List;
import java.util.UUID;

public record RiderDTO(UUID id, String name, List<DeliveryDTO> deliveries) {
    public static RiderDTO toDTO(Rider rider) {
        return new RiderDTO(rider.getId(), rider.getName(), rider.getDeliveries().stream().map(DeliveryDTO::toDTO).toList());
    }
}

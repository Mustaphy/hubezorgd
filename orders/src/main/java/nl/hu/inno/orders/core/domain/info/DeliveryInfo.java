package nl.hu.inno.orders.core.domain.info;

import jakarta.persistence.Embeddable;

import java.util.UUID;

@Embeddable
public record DeliveryInfo(UUID deliveryId) {
    public DeliveryInfo() {
        this(null);
    }
}

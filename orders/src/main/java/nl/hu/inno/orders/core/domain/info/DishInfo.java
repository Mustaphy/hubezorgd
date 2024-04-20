package nl.hu.inno.orders.core.domain.info;

import jakarta.persistence.Embeddable;

import java.util.UUID;

@Embeddable
public record DishInfo(UUID id) {
    public DishInfo() {
        this(null);
    }
}

package nl.hu.inno.delivery.core.domain.info;

import jakarta.persistence.Embeddable;

import java.util.UUID;

@Embeddable
public record OrderInfo(UUID id, String username) {
    public OrderInfo() {
        this(null, null);
    }
}

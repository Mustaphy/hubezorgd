package nl.hu.inno.orders.core.domain.event;

import java.util.UUID;

public class OrderReadyForDeliveryEvent extends OrderEvent {
    private final UUID order;
    private final String username;

    public OrderReadyForDeliveryEvent(UUID order, String username) {
        this.order = order;
        this.username = username;
    }

    @Override
    public String getEventKey() {
        return "order.ready-for-delivery";
    }

    public UUID getOrder() {
        return order;
    }

    public String getUsername() {
        return username;
    }
}

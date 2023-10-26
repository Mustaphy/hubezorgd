package nl.hu.inno.delivery.core.domain.event;

import java.util.UUID;

public class DeliveryUnderwayEvent extends DeliveryEvent {
    private final UUID order;
    private final UUID delivery;

    public DeliveryUnderwayEvent(UUID order, UUID delivery) {
        this.order = order;
        this.delivery = delivery;
    }

    @Override
    public String getEventKey() {
        return "delivery.underway";
    }

    public UUID getOrder() {
        return order;
    }

    public UUID getDelivery() {
        return delivery;
    }
}

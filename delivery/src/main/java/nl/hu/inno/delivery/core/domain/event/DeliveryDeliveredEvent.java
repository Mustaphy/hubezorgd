package nl.hu.inno.delivery.core.domain.event;

import java.util.UUID;

public class DeliveryDeliveredEvent extends DeliveryEvent {
    private final UUID order;

    public DeliveryDeliveredEvent(UUID order) {
        this.order = order;
    }

    @Override
    public String getEventKey() {
        return "delivery.delivered";
    }

    public UUID getOrder() {
        return order;
    }
}

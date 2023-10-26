package nl.hu.inno.stock.core.domain.event;

import java.util.UUID;

public class DishesPreparedEvent extends DishEvent {
    private final UUID order;

    public DishesPreparedEvent(UUID order) {
        this.order = order;
    }

    @Override
    public String getEventKey() {
        return "dish.prepared";
    }

    public UUID getOrder() {
        return order;
    }
}

package nl.hu.inno.stock.core.domain.event;

import java.util.UUID;

public class DishCreatedEvent extends DishEvent {
    private final UUID dish;

    public DishCreatedEvent(UUID dish) {
        this.dish = dish;
    }

    @Override
    public String getEventKey() {
        return "dish.created";
    }

    public UUID getDish() {
        return dish;
    }
}

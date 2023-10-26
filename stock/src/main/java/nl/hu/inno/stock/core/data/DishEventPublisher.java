package nl.hu.inno.stock.core.data;

import nl.hu.inno.stock.core.domain.event.DishEvent;

public interface DishEventPublisher {
    void publish(DishEvent event);
}

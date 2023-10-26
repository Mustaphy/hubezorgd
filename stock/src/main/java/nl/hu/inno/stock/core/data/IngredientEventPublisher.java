package nl.hu.inno.stock.core.data;

import nl.hu.inno.stock.core.domain.event.IngredientEvent;

public interface IngredientEventPublisher {
    void publish(IngredientEvent event);
}

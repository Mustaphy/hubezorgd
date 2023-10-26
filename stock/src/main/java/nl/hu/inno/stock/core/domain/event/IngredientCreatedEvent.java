package nl.hu.inno.stock.core.domain.event;

import java.util.UUID;

public class IngredientCreatedEvent extends IngredientEvent {
    private final UUID ingredient;

    public IngredientCreatedEvent(UUID ingredient) {
        this.ingredient = ingredient;
    }

    @Override
    public String getEventKey() {
        return "ingredient.created";
    }

    public UUID getIngredient() {
        return ingredient;
    }
}

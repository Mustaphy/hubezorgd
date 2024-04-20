package nl.hu.inno.stock.infrastructure.dto;

import nl.hu.inno.stock.core.domain.Dish;
import nl.hu.inno.stock.core.domain.Ingredient;

import java.util.List;
import java.util.UUID;

public record DishDTO(UUID id, String name, List<Ingredient> ingredients) {
    public static DishDTO toDTO(Dish dish) {
        return new DishDTO(dish.getId(), dish.getName(), dish.getIngredients());
    }
}

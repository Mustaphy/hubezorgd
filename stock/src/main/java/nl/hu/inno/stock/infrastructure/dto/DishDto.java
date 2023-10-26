package nl.hu.inno.stock.infrastructure.dto;

import nl.hu.inno.stock.core.domain.Dish;
import nl.hu.inno.stock.core.domain.Ingredient;

import java.util.List;
import java.util.UUID;

public record DishDto(UUID id, String name, List<Ingredient> ingredients) {
    public static DishDto toDto(Dish dish) {
        return new DishDto(dish.getId(), dish.getName(), dish.getIngredients());
    }
}

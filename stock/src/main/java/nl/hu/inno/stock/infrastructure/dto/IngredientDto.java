package nl.hu.inno.stock.infrastructure.dto;

import nl.hu.inno.stock.core.domain.Ingredient;

import java.util.UUID;

public record IngredientDto(UUID id, String name, boolean vegetarian, int nrInStock) {
    public static IngredientDto toDto(Ingredient ingredient) {
        return new IngredientDto(ingredient.getId(), ingredient.getName(), ingredient.isVegetarian(), ingredient.getNrInStock());
    }
}

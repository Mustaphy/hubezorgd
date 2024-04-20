package nl.hu.inno.stock.infrastructure.dto;

import nl.hu.inno.stock.core.domain.Ingredient;

import java.util.UUID;

public record IngredientDTO(UUID id, String name, boolean vegetarian, int nrInStock) {
    public static IngredientDTO toDTO(Ingredient ingredient) {
        return new IngredientDTO(ingredient.getId(), ingredient.getName(), ingredient.isVegetarian(), ingredient.getNrInStock());
    }
}

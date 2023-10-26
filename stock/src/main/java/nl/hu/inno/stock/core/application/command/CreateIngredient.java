package nl.hu.inno.stock.core.application.command;

public record CreateIngredient(String name, boolean vegetarian, int nrInStock) {
}

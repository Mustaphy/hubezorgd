package nl.hu.inno.stock.core.domain;

import nl.hu.inno.stock.core.domain.event.IngredientCreatedEvent;
import nl.hu.inno.stock.core.domain.event.IngredientEvent;
import nl.hu.inno.stock.core.domain.exception.OutOfStockException;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Document
public class Ingredient {
    @Id
    private UUID id;
    private String name;
    private boolean vegetarian;
    private int nrInStock;
    @Transient
    private List<IngredientEvent> events = new ArrayList<>();

    protected Ingredient() { }

    private Ingredient(String name, boolean vegetarian, int nrInStock) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.vegetarian = vegetarian;
        this.nrInStock = nrInStock;
    }

    public static Ingredient create(String name, boolean vegetarian, int nrInStock) {
        Ingredient ingredient = new Ingredient(name, vegetarian, nrInStock);

        ingredient.events.add(new IngredientCreatedEvent(ingredient.getId()));

        return ingredient;
    }

    public boolean isVegetarian() {
        return vegetarian;
    }

    public String getName() {
        return name;
    }

    public UUID getId() {
        return this.id;
    }

    public int getNrInStock() {
        return nrInStock;
    }

    public List<IngredientEvent> listEvents() {
        return events;
    }

    public void clearEvents() {
        this.events.clear();
    }

    public void take(int nr) {
        if (nr > this.nrInStock) {
            throw new OutOfStockException("Out of stock: " + this.getName());
        }

        this.nrInStock -= nr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ingredient that = (Ingredient) o;
        return vegetarian == that.vegetarian && Objects.equals(id, that.id) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

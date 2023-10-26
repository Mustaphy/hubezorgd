package nl.hu.inno.stock.core.domain;

import nl.hu.inno.stock.core.domain.event.DishCreatedEvent;
import nl.hu.inno.stock.core.domain.event.DishEvent;
import nl.hu.inno.stock.core.domain.exception.NoIngredientsException;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.*;

@Document
public class Dish {
    @Id
    private UUID id;
    private String name;
    @DocumentReference(lazy = true)
    private List<Ingredient> ingredients;
    @Transient
    private List<DishEvent> events = new ArrayList<>();

    public Dish() { }

    private Dish(String name, List<Ingredient> ingredients) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.ingredients = ingredients;
    }

    public static Dish create(String name, List<Ingredient> ingredients) {
        Dish dish = new Dish(name, ingredients);

        dish.events.add(new DishCreatedEvent(dish.getId()));

        Dish.validate(dish);

        return dish;
    }

    public static void validate(Dish dish) {
        if (dish.ingredients.size() == 0) {
            throw new NoIngredientsException("Cannot have 0 ingredients.");
        }
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Ingredient> getIngredients() {
        return Collections.unmodifiableList(ingredients);
    }

    public void prepare(int nr) {
        for (Ingredient ingredient : this.ingredients) {
            ingredient.take(nr);
        }
    }

    public List<DishEvent> listEvents() {
        return events;
    }

    public void clearEvents() {
        this.events.clear();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dish dish = (Dish) o;
        return Objects.equals(id, dish.id) && Objects.equals(name, dish.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

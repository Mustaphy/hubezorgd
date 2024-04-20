package nl.hu.inno.stock.core.domain;

import jakarta.persistence.*;
import nl.hu.inno.stock.core.domain.event.DishCreatedEvent;
import nl.hu.inno.stock.core.domain.event.DishEvent;
import nl.hu.inno.stock.core.domain.exception.NoIngredientsException;
import org.springframework.data.annotation.Transient;

import java.util.*;

@Entity
public class Dish {
    @Id
    private UUID id;
    private String name;
    @ManyToMany(cascade = CascadeType.PERSIST)
    private List<Ingredient> ingredients;
    @ElementCollection
    @Transient
    private List<DishEvent> events = new ArrayList<>();

    protected Dish() { }

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
        if (dish.ingredients.isEmpty()) {
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
        this.ingredients.forEach(ingredient -> ingredient.take(nr));
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

package nl.hu.inno.orders.core.domain;

import jakarta.persistence.*;
import nl.hu.inno.orders.core.domain.info.DishInfo;

import java.io.Serializable;
import java.util.UUID;

@Entity
public class OrderedDish {
    @Embeddable
    public static class OrderedDishId implements Serializable {
        @Embedded
        private DishInfo dish;
        @ManyToOne
        private Order order;
    }

    @EmbeddedId
    private OrderedDishId id;
    private int nr;

    protected OrderedDish() { }

    private OrderedDish(Order order, DishInfo dish, int nr) {
        this.id = new OrderedDishId();
        this.id.dish = dish;
        this.id.order = order;
        this.nr = nr;
    }

    public static OrderedDish create(Order order, DishInfo dish, int nr) {
        return new OrderedDish(order, dish, nr);
    }

    public int getNr() {
        return nr;
    }

    public Order getOrder() {
        return this.id.order;
    }

    public UUID getDishId() {
        return this.id.dish.id();
    }

    public DishInfo getDish() {
        return this.id.dish;
    }
}

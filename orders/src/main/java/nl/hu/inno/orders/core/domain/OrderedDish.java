package nl.hu.inno.orders.core.domain;

import nl.hu.inno.orders.core.domain.info.DishInfo;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.io.Serializable;
import java.util.UUID;

@Document
public class OrderedDish {
    public static class OrderedDishId implements Serializable {
        private DishInfo dish;
        @DocumentReference
        private Order order;
    }

    private OrderedDishId id;
    private int nr;

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

package nl.hu.inno.orders.core.domain.event;

import nl.hu.inno.orders.core.domain.OrderedDish;
import nl.hu.inno.orders.infrastructure.dto.OrderedDishDto;

import java.util.List;
import java.util.UUID;

public class OrderPlacedEvent extends OrderEvent {
    private final UUID order;
    private final List<OrderedDishDto> orderedDishes;

    public OrderPlacedEvent(UUID order, List<OrderedDish> orderedDishes) {
        this.order = order;
        this.orderedDishes = orderedDishes.stream().map(OrderedDishDto::toDto).toList();
    }

    @Override
    public String getEventKey() {
        return "order.created";
    }

    public UUID getOrder() {
        return order;
    }

    public List<OrderedDishDto> getOrderedDishes() {
        return orderedDishes;
    }
}

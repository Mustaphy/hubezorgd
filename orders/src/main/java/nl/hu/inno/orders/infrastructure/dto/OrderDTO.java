package nl.hu.inno.orders.infrastructure.dto;

import nl.hu.inno.orders.core.domain.OrderStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public abstract class OrderDTO {
    private final UUID id;
    private final OrderStatus status;
    private final LocalDateTime orderDate;
    private final List<OrderedDishDTO> orderedDishes;

    public OrderDTO(UUID id, OrderStatus status, LocalDateTime orderDate, List<OrderedDishDTO> orderedDishes) {
        this.id = id;
        this.status = status;
        this.orderDate = orderDate;
        this.orderedDishes = orderedDishes;
    }

    public UUID getId() {
        return id;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public List<OrderedDishDTO> getOrderedDishes() {
        return orderedDishes;
    }
}

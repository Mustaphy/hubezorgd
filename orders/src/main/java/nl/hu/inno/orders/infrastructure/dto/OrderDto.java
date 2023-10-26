package nl.hu.inno.orders.infrastructure.dto;

import nl.hu.inno.orders.core.domain.OrderStatus;
import nl.hu.inno.orders.core.domain.OrderedDish;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public abstract class OrderDto {
    private UUID id;
    private OrderStatus status;
    private LocalDateTime orderDate;
    private List<OrderedDishDto> orderedDishes;

    public OrderDto(UUID id, OrderStatus status, LocalDateTime orderDate, List<OrderedDishDto> orderedDishes) {
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

    public List<OrderedDishDto> getOrderedDishes() {
        return orderedDishes;
    }
}

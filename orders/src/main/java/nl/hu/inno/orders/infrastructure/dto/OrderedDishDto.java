package nl.hu.inno.orders.infrastructure.dto;

import nl.hu.inno.orders.core.domain.OrderedDish;

import java.util.UUID;

public record OrderedDishDto(UUID id, int nr) {
    public static OrderedDishDto toDto(OrderedDish orderedDish) {
        return new OrderedDishDto(orderedDish.getDishId(), orderedDish.getNr());
    }
}

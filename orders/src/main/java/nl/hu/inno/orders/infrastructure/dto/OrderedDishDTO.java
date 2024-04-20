package nl.hu.inno.orders.infrastructure.dto;

import nl.hu.inno.orders.core.domain.OrderedDish;

import java.util.UUID;

public record OrderedDishDTO(UUID id, int nr) {
    public static OrderedDishDTO toDTO(OrderedDish orderedDish) {
        return new OrderedDishDTO(orderedDish.getDishId(), orderedDish.getNr());
    }
}

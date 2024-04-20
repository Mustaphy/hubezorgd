package nl.hu.inno.orders.infrastructure.dto;

import nl.hu.inno.orders.core.domain.Order;

public class OrderWithoutDeliveryDTO extends OrderDTO {
    public OrderWithoutDeliveryDTO(Order order) {
        super(order.getId(), order.getStatus(), order.getOrderDate(), order.getOrderedDishes()
                .stream()
                .map(orderedDish -> new OrderedDishDTO(orderedDish.getDishId(), orderedDish.getNr()))
                .toList());
    }
}

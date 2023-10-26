package nl.hu.inno.orders.infrastructure.dto;

import nl.hu.inno.orders.core.domain.Order;

public class OrderWithoutDeliveryDto extends OrderDto {
    public OrderWithoutDeliveryDto(Order order) {
        super(order.getId(), order.getStatus(), order.getOrderDate(), order.getOrderedDishes()
                .stream()
                .map(orderedDish -> new OrderedDishDto(orderedDish.getDishId(), orderedDish.getNr()))
                .toList());
    }
}

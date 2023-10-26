package nl.hu.inno.orders.infrastructure.dto;

import nl.hu.inno.orders.core.domain.Order;

public class OrderWithDeliveryDto extends OrderDto {
    private String deliveryLink;

    public OrderWithDeliveryDto(Order order) {
        super(order.getId(), order.getStatus(), order.getOrderDate(), order.getOrderedDishes()
                .stream()
                .map(OrderedDishDto::toDto)
                .toList());
        this.deliveryLink = String.format("http://localhost:8082/delivery/%s", order.getDelivery().id());
    }

    public String getDeliveryLink() {
        return deliveryLink;
    }
}

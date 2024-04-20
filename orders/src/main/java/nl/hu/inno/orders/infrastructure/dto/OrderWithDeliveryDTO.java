package nl.hu.inno.orders.infrastructure.dto;

import nl.hu.inno.orders.core.domain.Order;

public class OrderWithDeliveryDTO extends OrderDTO {
    private final String deliveryLink;

    public OrderWithDeliveryDTO(Order order) {
        super(order.getId(), order.getStatus(), order.getOrderDate(), order.getOrderedDishes()
                .stream()
                .map(OrderedDishDTO::toDTO)
                .toList());

        this.deliveryLink = String.format("http://localhost:8082/delivery/%s", order.getDelivery().deliveryId());
    }

    public String getDeliveryLink() {
        return deliveryLink;
    }
}

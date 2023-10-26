package nl.hu.inno.orders.infrastructure.dto.factory;

import nl.hu.inno.orders.core.domain.Order;
import nl.hu.inno.orders.infrastructure.dto.OrderDto;
import nl.hu.inno.orders.infrastructure.dto.OrderWithDeliveryDto;
import nl.hu.inno.orders.infrastructure.dto.OrderWithoutDeliveryDto;

public class DeliveryBasedOrderDtoFactory {
    public static OrderDto create(Order order) {
        if (order.getDelivery() == null) {
            return new OrderWithoutDeliveryDto(order);
        }

        return new OrderWithDeliveryDto(order);
    }
}

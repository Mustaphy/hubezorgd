package nl.hu.inno.orders.infrastructure.dto.factory;

import nl.hu.inno.orders.core.domain.Order;
import nl.hu.inno.orders.infrastructure.dto.OrderDTO;
import nl.hu.inno.orders.infrastructure.dto.OrderWithDeliveryDTO;
import nl.hu.inno.orders.infrastructure.dto.OrderWithoutDeliveryDTO;

public class DeliveryBasedOrderDTOFactory {
    public static OrderDTO create(Order order) {
        if (order.getDelivery() == null) {
            return new OrderWithoutDeliveryDTO(order);
        }

        return new OrderWithDeliveryDTO(order);
    }
}

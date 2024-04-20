package nl.hu.inno.orders.core.application;

import nl.hu.inno.orders.core.application.query.GetOrderById;
import nl.hu.inno.orders.core.application.query.GetOrders;
import nl.hu.inno.orders.core.application.query.IsDelivered;
import nl.hu.inno.orders.core.data.storage.OrderRepository;
import nl.hu.inno.orders.core.domain.OrderStatus;
import nl.hu.inno.orders.core.domain.exception.OrderNotFoundException;
import nl.hu.inno.orders.infrastructure.dto.OrderDTO;
import nl.hu.inno.orders.infrastructure.dto.factory.DeliveryBasedOrderDTOFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderQueryHandler {
    private final OrderRepository orderRepository;

    public OrderQueryHandler(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<OrderDTO> handle(GetOrders query) {
        return this.orderRepository
                .findAllByUser(query.user())
                .stream()
                .map(DeliveryBasedOrderDTOFactory::create)
                .toList();
    }

    public OrderDTO handle(GetOrderById query) {
        return DeliveryBasedOrderDTOFactory.create(this.orderRepository
                .findByIdAndUser(query.id(), query.user())
                .orElseThrow(() -> new OrderNotFoundException(String.format("Order with deliveryId '%s' could not be found.", query.id()))));
    }

    public boolean handle(IsDelivered query) {
        return this.orderRepository
                .findById(query.id())
                .orElseThrow(() -> new OrderNotFoundException(String.format("Order with deliveryId '%s' could not be found.", query.id())))
                .getStatus()
                .equals(OrderStatus.DELIVERED);
    }
}

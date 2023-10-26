package nl.hu.inno.orders.core.application;

import nl.hu.inno.orders.core.application.query.GetOrderById;
import nl.hu.inno.orders.core.application.query.GetOrders;
import nl.hu.inno.orders.core.application.query.IsDelivered;
import nl.hu.inno.orders.core.data.storage.OrderRepository;
import nl.hu.inno.orders.core.domain.Order;
import nl.hu.inno.orders.core.domain.OrderStatus;
import nl.hu.inno.orders.core.domain.exception.OrderNotFoundException;
import nl.hu.inno.orders.infrastructure.dto.OrderDto;
import nl.hu.inno.orders.infrastructure.dto.OrderedDishDto;
import nl.hu.inno.orders.infrastructure.dto.factory.DeliveryBasedOrderDtoFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderQueryHandler {
    private final OrderRepository orderRepository;

    public OrderQueryHandler(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<OrderDto> handle(GetOrders query) {
        return this.orderRepository.findAll()
                .stream()
                .map(DeliveryBasedOrderDtoFactory::create)
                .toList();
    }

    public OrderDto handle(GetOrderById query) {
        return DeliveryBasedOrderDtoFactory.create(this.orderRepository.findById(query.id())
                .orElseThrow(() -> new OrderNotFoundException(String.format("Order with id '%s' could not be found.", query.id()))));
    }

    public boolean handle(IsDelivered query) {
        return this.orderRepository.findById(query.id())
                .orElseThrow(() -> new OrderNotFoundException(String.format("Order with id '%s' could not be found.", query.id())))
                .getStatus()
                .equals(OrderStatus.DELIVERED);
    }
}

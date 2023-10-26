package nl.hu.inno.orders.core.application;

import nl.hu.inno.orders.core.application.command.OrderDelivered;
import nl.hu.inno.orders.core.application.command.OrderUnderway;
import nl.hu.inno.orders.core.application.command.PlaceOrder;
import nl.hu.inno.orders.core.application.command.OrderReadyForDelivery;
import nl.hu.inno.orders.core.data.OrderEventPublisher;
import nl.hu.inno.orders.core.data.storage.OrderRepository;
import nl.hu.inno.orders.core.domain.Order;
import nl.hu.inno.orders.core.domain.exception.OrderNotFoundException;
import nl.hu.inno.orders.core.domain.exception.OutOfStockException;
import nl.hu.inno.orders.core.domain.info.DeliveryInfo;
import nl.hu.inno.orders.core.domain.info.DishInfo;
import nl.hu.inno.orders.core.domain.event.OrderEvent;
import nl.hu.inno.orders.infrastructure.dto.OrderDto;
import nl.hu.inno.orders.infrastructure.dto.factory.DeliveryBasedOrderDtoFactory;
import nl.hu.inno.orders.infrastructure.gateway.HttpStockGateway;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
public class OrderCommandHandler {
    private final OrderRepository orderRepository;
    private final HttpStockGateway stockGateway;
    private final OrderEventPublisher eventPublisher;

    public OrderCommandHandler(OrderRepository orderRepository, HttpStockGateway stockGateway, OrderEventPublisher eventPublisher) {
        this.orderRepository = orderRepository;
        this.stockGateway = stockGateway;
        this.eventPublisher = eventPublisher;
    }

    public OrderDto handle(PlaceOrder command) {
        Order order = Order.create(command.user(), LocalDateTime.now(), command.dishes().stream().map(DishInfo::new).toList());

        if (!stockGateway.checkStock(order.getOrderedDishes())) {
            throw new OutOfStockException("At least one of the dishes is out of stock");
        }

        this.publishEventsAndSave(order);

        return DeliveryBasedOrderDtoFactory.create(order);
    }

    public void handle(OrderReadyForDelivery command) {
        Order order = this.orderRepository
                .findById(command.id())
                .orElseThrow(() -> new OrderNotFoundException(String.format("Order with id '%s' could not be found", command.id())));

        order.readyForDelivery();

        this.publishEventsAndSave(order);
    }

    public void handle(OrderUnderway command) {
        Order order = this.orderRepository
                .findById(command.id())
                .orElseThrow(() -> new OrderNotFoundException(String.format("Order with id '%s' could not be found.", command.id())));

        order.underway(new DeliveryInfo(command.delivery()));

        this.publishEventsAndSave(order);
    }

    public void handle(OrderDelivered command) {
        Order order = this.orderRepository
                .findById(command.id())
                .orElseThrow(() -> new OrderNotFoundException(String.format("Order with id '%s' could not be found.", command.id())));

        order.delivered();

        this.publishEventsAndSave(order);
    }

    private void publishEventsAndSave(Order order) {
        List<OrderEvent> events = order.listEvents();
        events.forEach(this.eventPublisher::publish);
        order.clearEvents();

        this.orderRepository.save(order);
    }
}

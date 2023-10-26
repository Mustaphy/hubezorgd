package nl.hu.inno.orders.infrastructure.messaging;

import nl.hu.inno.orders.core.application.OrderCommandHandler;
import nl.hu.inno.orders.core.application.command.NextOrderStatus;
import nl.hu.inno.orders.core.application.command.OrderDelivered;
import nl.hu.inno.orders.core.application.command.OrderReadyForDelivery;
import nl.hu.inno.orders.core.application.command.OrderUnderway;
import nl.hu.inno.orders.infrastructure.messaging.event.DeliveryEvent;
import nl.hu.inno.orders.infrastructure.messaging.event.DishEvent;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMqEventListener {
    private final OrderCommandHandler orderCommandHandler;

    public RabbitMqEventListener(OrderCommandHandler orderCommandHandler) {
        this.orderCommandHandler = orderCommandHandler;
    }

    @RabbitListener(queues = "#{'${messaging.queue.stock}'}")
    public void listen(DishEvent event) {
        switch (event.eventKey) {
            case "dish.prepared" -> this.orderCommandHandler.handle(new OrderReadyForDelivery(event.order));
        }
    }

    @RabbitListener(queues = "#{'${messaging.queue.delivery}'}")
    public void listen(DeliveryEvent event) {
        switch (event.eventKey) {
            case "delivery.underway" -> this.orderCommandHandler.handle(new OrderUnderway(event.order, event.delivery));
            case "delivery.delivered" -> this.orderCommandHandler.handle(new OrderDelivered(event.order));
        }
    }
}

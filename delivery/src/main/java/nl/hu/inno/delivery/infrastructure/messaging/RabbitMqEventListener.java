package nl.hu.inno.delivery.infrastructure.messaging;

import nl.hu.inno.delivery.core.application.DeliveryCommandHandler;
import nl.hu.inno.delivery.core.application.command.ScheduleDelivery;
import nl.hu.inno.delivery.infrastructure.messaging.event.OrderEvent;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMqEventListener {
    private final DeliveryCommandHandler deliveryCommandHandler;

    public RabbitMqEventListener(DeliveryCommandHandler deliveryCommandHandler) {
        this.deliveryCommandHandler = deliveryCommandHandler;
    }

    @RabbitListener(queues = "#{'${messaging.queue.orders}'}")
    public void listen(OrderEvent event) {
        switch (event.eventKey) {
            case "order.ready-for-delivery" -> this.deliveryCommandHandler.handle(new ScheduleDelivery(event.order, event.username));
        }
    }
}

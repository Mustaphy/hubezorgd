package nl.hu.inno.orders.infrastructure.messaging;

import nl.hu.inno.orders.core.data.OrderEventPublisher;
import nl.hu.inno.orders.core.domain.event.OrderEvent;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

public class RabbitMqEventPublisher implements OrderEventPublisher {
    private final RabbitTemplate rabbitTemplate;
    private final String ordersQueue;

    public RabbitMqEventPublisher(RabbitTemplate rabbitTemplate, String ordersQueue) {
        this.rabbitTemplate = rabbitTemplate;
        this.ordersQueue = ordersQueue;
    }

    public void publish(OrderEvent event) {
        this.rabbitTemplate.convertAndSend(this.ordersQueue, event);
    }
}


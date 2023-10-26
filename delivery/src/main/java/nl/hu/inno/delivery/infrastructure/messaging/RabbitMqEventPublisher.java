package nl.hu.inno.delivery.infrastructure.messaging;

import nl.hu.inno.delivery.core.data.DeliveryEventPublisher;
import nl.hu.inno.delivery.core.domain.event.DeliveryEvent;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

public class RabbitMqEventPublisher implements DeliveryEventPublisher {
    private final RabbitTemplate rabbitTemplate;
    private final String deliveryQueue;

    public RabbitMqEventPublisher(RabbitTemplate rabbitTemplate, String deliveryQueue) {
        this.rabbitTemplate = rabbitTemplate;
        this.deliveryQueue = deliveryQueue;
    }

    public void publish(DeliveryEvent event) {
        this.rabbitTemplate.convertAndSend(this.deliveryQueue, event);
    }
}


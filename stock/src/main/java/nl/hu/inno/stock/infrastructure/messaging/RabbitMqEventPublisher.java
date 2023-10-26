package nl.hu.inno.stock.infrastructure.messaging;

import nl.hu.inno.stock.core.data.DishEventPublisher;
import nl.hu.inno.stock.core.data.IngredientEventPublisher;
import nl.hu.inno.stock.core.domain.event.DishEvent;
import nl.hu.inno.stock.core.domain.event.IngredientEvent;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

public class RabbitMqEventPublisher implements DishEventPublisher, IngredientEventPublisher {
    private final RabbitTemplate rabbitTemplate;
    private final String stockQueue;

    public RabbitMqEventPublisher(RabbitTemplate rabbitTemplate, String stockQueue) {
        this.rabbitTemplate = rabbitTemplate;
        this.stockQueue = stockQueue;
    }

    public void publish(DishEvent event) {
        this.rabbitTemplate.convertAndSend(this.stockQueue, event);
    }

    public void publish(IngredientEvent event) {
        this.rabbitTemplate.convertAndSend(this.stockQueue, event);
    }
}

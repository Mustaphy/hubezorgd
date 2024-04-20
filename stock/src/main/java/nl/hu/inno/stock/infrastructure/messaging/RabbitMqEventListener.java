package nl.hu.inno.stock.infrastructure.messaging;

import nl.hu.inno.stock.core.application.DishCommandHandler;
import nl.hu.inno.stock.core.application.command.PrepareDishes;
import nl.hu.inno.stock.infrastructure.messaging.event.OrderEvent;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitMqEventListener {
    private final DishCommandHandler dishCommandHandler;

    public RabbitMqEventListener(DishCommandHandler dishCommandHandler) {
        this.dishCommandHandler = dishCommandHandler;
    }

    @RabbitListener(queues = "#{'${messaging.queue.orders}'}")
    public void listen(OrderEvent event) {
        switch (event.eventKey) {
            case "order.created" -> this.dishCommandHandler.handle(new PrepareDishes(event.order, event.orderedDishes));
        }
    }
}

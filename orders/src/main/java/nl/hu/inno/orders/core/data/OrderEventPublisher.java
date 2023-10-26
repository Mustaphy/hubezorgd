package nl.hu.inno.orders.core.data;

import nl.hu.inno.orders.core.domain.event.OrderEvent;

public interface OrderEventPublisher {
    void publish(OrderEvent event);
}

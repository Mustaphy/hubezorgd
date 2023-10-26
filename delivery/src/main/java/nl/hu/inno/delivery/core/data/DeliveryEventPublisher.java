package nl.hu.inno.delivery.core.data;

import nl.hu.inno.delivery.core.domain.event.DeliveryEvent;

public interface DeliveryEventPublisher {
    void publish(DeliveryEvent event);
}

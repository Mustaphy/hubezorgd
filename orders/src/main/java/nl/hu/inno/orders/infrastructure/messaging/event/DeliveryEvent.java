package nl.hu.inno.orders.infrastructure.messaging.event;

import java.time.Instant;
import java.util.UUID;

public class DeliveryEvent {
    public UUID eventId;
    public String eventKey;
    public Instant eventDate;

    public UUID order;
    public UUID delivery;
}

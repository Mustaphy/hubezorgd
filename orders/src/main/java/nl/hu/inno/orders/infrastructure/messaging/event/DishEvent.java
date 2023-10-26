package nl.hu.inno.orders.infrastructure.messaging.event;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public class DishEvent {
    public UUID eventId;
    public String eventKey;
    public Instant eventDate;

    public UUID order;
}

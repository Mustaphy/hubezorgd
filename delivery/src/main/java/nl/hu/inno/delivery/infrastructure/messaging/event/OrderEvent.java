package nl.hu.inno.delivery.infrastructure.messaging.event;

import java.time.Instant;
import java.util.UUID;

public class OrderEvent {
    public UUID eventId;
    public String eventKey;
    public Instant eventDate;

    public UUID order;
    public String username;
}

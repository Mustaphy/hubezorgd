package nl.hu.inno.stock.infrastructure.messaging.event;

import nl.hu.inno.stock.infrastructure.dto.OrderedDishDTO;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public class OrderEvent {
    public UUID eventId;
    public String eventKey;
    public Instant eventDate;

    public UUID order;
    public List<OrderedDishDTO> orderedDishes;
}

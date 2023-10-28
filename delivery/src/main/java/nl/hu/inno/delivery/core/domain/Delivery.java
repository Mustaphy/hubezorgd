package nl.hu.inno.delivery.core.domain;

import nl.hu.inno.delivery.core.domain.event.DeliveryDeliveredEvent;
import nl.hu.inno.delivery.core.domain.event.DeliveryEvent;
import nl.hu.inno.delivery.core.domain.event.DeliveryUnderwayEvent;
import nl.hu.inno.delivery.core.domain.info.OrderInfo;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Document
public class Delivery {
    @Id
    private UUID id;
    private LocalDateTime estimatedDelivery;
    @DocumentReference(lazy = true)
    private Rider rider;
    private OrderInfo order;
    @Transient
    private List<DeliveryEvent> events = new ArrayList<>();

    private Delivery() { }

    private Delivery(LocalDateTime estimatedDelivery, Rider rider, OrderInfo order) {
        this.id = UUID.randomUUID();
        this.estimatedDelivery = estimatedDelivery;
        this.rider = rider;
        this.order = order;
    }

    public static Delivery create(LocalDateTime estimatedDelivery, Rider rider, OrderInfo order) {
        Delivery delivery = new Delivery(estimatedDelivery, rider, order);

        rider.addDelivery(delivery);
        delivery.events.add(new DeliveryUnderwayEvent(order.id(), delivery.id));

        return delivery;
    }

    public UUID getId() {
        return id;
    }

    public LocalDateTime getEstimatedDelivery() {
        return estimatedDelivery;
    }

    public Rider getRider() {
        return rider;
    }

    public OrderInfo getOrder() {
        return order;
    }

    public boolean isDelivered() {
        return LocalDateTime
                .now()
                .isAfter(this.estimatedDelivery);
    }

    public void deliver() {
        this.events.add(new DeliveryDeliveredEvent(order.id()));
    }

    public List<DeliveryEvent> listEvents() {
        return events;
    }

    public void clearEvents() {
        this.events.clear();
    }
}

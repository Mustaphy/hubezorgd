package nl.hu.inno.delivery.core.domain;

import jakarta.persistence.*;
import nl.hu.inno.delivery.core.domain.event.DeliveryDeliveredEvent;
import nl.hu.inno.delivery.core.domain.event.DeliveryEvent;
import nl.hu.inno.delivery.core.domain.event.DeliveryUnderwayEvent;
import nl.hu.inno.delivery.core.domain.info.OrderInfo;
import org.springframework.data.annotation.Transient;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class Delivery {
    @Id
    private UUID deliveryId;
    private LocalDateTime estimatedDelivery;
    @ManyToOne
    private Rider rider;
    private OrderInfo order;
    @ElementCollection
    @Transient
    private List<DeliveryEvent> events = new ArrayList<>();

    protected Delivery() { }

    private Delivery(LocalDateTime estimatedDelivery, Rider rider, OrderInfo order) {
        this.deliveryId = UUID.randomUUID();
        this.estimatedDelivery = estimatedDelivery;
        this.rider = rider;
        this.order = order;
    }

    public static Delivery create(LocalDateTime estimatedDelivery, Rider rider, OrderInfo order) {
        Delivery delivery = new Delivery(estimatedDelivery, rider, order);

        rider.addDelivery(delivery);
        delivery.events.add(new DeliveryUnderwayEvent(order.id(), delivery.deliveryId));

        return delivery;
    }

    public UUID getDeliveryId() {
        return deliveryId;
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

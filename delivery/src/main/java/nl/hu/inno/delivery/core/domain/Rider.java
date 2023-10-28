package nl.hu.inno.delivery.core.domain;

import nl.hu.inno.delivery.core.domain.event.DeliveryEvent;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Document
public class Rider {
    @Id
    private UUID id;
    private String name;
    @DocumentReference(lazy = true)
    private List<Delivery> deliveries = new ArrayList<>();
    @Transient
    private List<DeliveryEvent> events = new ArrayList<>();

    public Rider() { }

    private Rider(String name) {
        this.id = UUID.randomUUID();
        this.name = name;
    }

    public static Rider create(String name) {
        return new Rider(name);
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Delivery> getDeliveries() {
        return deliveries;
    }

    public int getNrOfDeliveries() {
        return deliveries.size();
    }

    public void addDelivery(Delivery delivery) {
        if (!this.deliveries.contains(delivery)) {
            this.deliveries.add(delivery);
        }
    }

    public List<DeliveryEvent> listEvents() {
        return events;
    }

    public void clearEvents() {
        this.events.clear();
    }
}

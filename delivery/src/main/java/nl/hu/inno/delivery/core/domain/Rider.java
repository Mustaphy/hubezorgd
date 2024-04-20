package nl.hu.inno.delivery.core.domain;

import jakarta.persistence.*;
import nl.hu.inno.delivery.core.domain.event.DeliveryEvent;
import org.springframework.data.annotation.Transient;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class Rider {
    @Id
    private UUID id;
    private String name;
    @OneToMany
    private List<Delivery> deliveries = new ArrayList<>();
    @ElementCollection
    @Transient
    private List<DeliveryEvent> events = new ArrayList<>();

    protected Rider() { }

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

package nl.hu.inno.delivery.core.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import nl.hu.inno.common.security.User;

import java.util.UUID;

@Entity
public class DeliveryReview {
    @Id
    private UUID id;
    @ManyToOne
    private Delivery delivery;
    private ReviewRating rating;
    private String description;
    @ManyToOne
    private User user;

    protected DeliveryReview() { }

    public DeliveryReview(Delivery delivery, ReviewRating rating, String description, User user) {
        this.id = UUID.randomUUID();
        this.delivery = delivery;
        this.rating = rating;
        this.description = description;
        this.user = user;
    }

    public UUID getId() {
        return id;
    }

    public Delivery getDelivery() {
        return delivery;
    }

    public ReviewRating getRating() {
        return rating;
    }

    public String getDescription() {
        return description;
    }

    public User getUser() {
        return user;
    }
}

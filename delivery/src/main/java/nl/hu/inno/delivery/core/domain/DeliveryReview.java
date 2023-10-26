package nl.hu.inno.delivery.core.domain;

import nl.hu.inno.common.security.User;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.UUID;

@Document
public class DeliveryReview {
    @Id
    private UUID id;
    @DocumentReference
    private Delivery delivery;
    private ReviewRating rating;
    private String description;
    private User user;

    private DeliveryReview() { }

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

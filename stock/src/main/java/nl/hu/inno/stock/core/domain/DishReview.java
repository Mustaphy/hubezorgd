package nl.hu.inno.stock.core.domain;

import nl.hu.inno.common.security.User;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.UUID;

@Document
public class DishReview {
    @Id
    private UUID id;
    @DocumentReference
    private Dish dish;
    private ReviewRating rating;
    private String description;
    private User user;

    public DishReview(Dish dish, ReviewRating rating, String description, User user) {
        this.id = UUID.randomUUID();
        this.dish = dish;
        this.rating = rating;
        this.description = description;
        this.user = user;
    }

    public UUID getId() {
        return id;
    }

    public Dish getDish() {
        return dish;
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

package nl.hu.inno.stock.core.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import nl.hu.inno.common.security.User;

import java.util.UUID;

@Entity
public class DishReview {
    @Id
    private UUID id;
    @ManyToOne
    private Dish dish;
    private ReviewRating rating;
    private String description;
    @ManyToOne
    private User user;

    protected DishReview() { }

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

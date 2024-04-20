package nl.hu.inno.stock.infrastructure.dto;

import nl.hu.inno.stock.core.domain.DishReview;
import nl.hu.inno.stock.core.domain.ReviewRating;

import java.util.UUID;

public record DishReviewDTO(UUID id, ReviewRating rating, String description, String username) {
    public static DishReviewDTO toDTO(DishReview review) {
        return new DishReviewDTO(review.getId(), review.getRating(), review.getDescription(), review.getUser().getUsername());
    }
}

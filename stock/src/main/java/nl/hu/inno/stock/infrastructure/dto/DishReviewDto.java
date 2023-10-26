package nl.hu.inno.stock.infrastructure.dto;

import nl.hu.inno.stock.core.domain.DishReview;
import nl.hu.inno.stock.core.domain.ReviewRating;

import java.util.UUID;

public record DishReviewDto(UUID id, ReviewRating rating, String description, String username) {
    public static DishReviewDto toDto(DishReview review) {
        return new DishReviewDto(review.getId(), review.getRating(), review.getDescription(), review.getUser().getUsername());
    }
}

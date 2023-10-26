package nl.hu.inno.delivery.infrastructure.dto;

import nl.hu.inno.common.security.User;
import nl.hu.inno.delivery.core.domain.DeliveryReview;
import nl.hu.inno.delivery.core.domain.ReviewRating;

import java.util.UUID;

public record DeliveryReviewDto(UUID id, ReviewRating rating, String description, String username) {
    public static DeliveryReviewDto toDto(DeliveryReview review) {
        return new DeliveryReviewDto(review.getId(), review.getRating(), review.getDescription(), review.getUser().getUsername());
    }
}

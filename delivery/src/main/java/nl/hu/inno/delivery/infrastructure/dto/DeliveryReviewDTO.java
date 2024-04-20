package nl.hu.inno.delivery.infrastructure.dto;

import nl.hu.inno.delivery.core.domain.DeliveryReview;
import nl.hu.inno.delivery.core.domain.ReviewRating;

import java.util.UUID;

public record DeliveryReviewDTO(UUID id, ReviewRating rating, String description, String username) {
    public static DeliveryReviewDTO toDTO(DeliveryReview review) {
        return new DeliveryReviewDTO(review.getId(), review.getRating(), review.getDescription(), review.getUser().getUsername());
    }
}

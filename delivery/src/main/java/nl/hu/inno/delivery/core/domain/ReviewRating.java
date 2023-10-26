package nl.hu.inno.delivery.core.domain;

import nl.hu.inno.delivery.core.domain.exception.InvalidRatingException;

public enum ReviewRating {
    HORRIBLE,
    POOR,
    AVERAGE,
    GOOD,
    EXCELLENT;

    public static ReviewRating fromInt(int rating) {
        return switch (rating) {
            case 1 -> HORRIBLE;
            case 2 -> POOR;
            case 3 -> AVERAGE;
            case 4 -> GOOD;
            case 5 -> EXCELLENT;
            default -> throw new InvalidRatingException(String.format("Rating of value '%s' is invalid. Rating must be between 1 and 5.", rating));
        };
    }

    public int toInt() {
        return switch (this) {
            case HORRIBLE -> 1;
            case POOR -> 2;
            case AVERAGE -> 3;
            case GOOD -> 4;
            case EXCELLENT -> 5;
        };
    }
}

package nl.hu.inno.delivery.core.application.command;

import nl.hu.inno.common.security.User;

import java.util.UUID;

public record PostDeliveryReview(UUID id, int rating, String description, User user) {
}

package nl.hu.inno.stock.core.application.command;

import nl.hu.inno.common.security.User;

import java.util.UUID;

public record PostDishReview(UUID id, int rating, String description, User user) {
}

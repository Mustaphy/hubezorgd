package nl.hu.inno.orders.core.application.query;

import nl.hu.inno.common.security.User;

import java.util.UUID;

public record GetOrderById(User user, UUID id) {
}

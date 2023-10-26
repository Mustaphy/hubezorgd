package nl.hu.inno.orders.core.application.command;

import nl.hu.inno.common.security.User;

import java.util.List;
import java.util.UUID;

public record PlaceOrder(User user, List<UUID> dishes) {
}

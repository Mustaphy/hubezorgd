package nl.hu.inno.delivery.core.application.query;

import nl.hu.inno.common.security.User;

import java.util.UUID;

public record GetDeliveryById(User user, UUID id) {
}

package nl.hu.inno.delivery.core.application.command;

import nl.hu.inno.common.security.User;

import java.util.UUID;

public record ScheduleDelivery(UUID order, String username) {
}

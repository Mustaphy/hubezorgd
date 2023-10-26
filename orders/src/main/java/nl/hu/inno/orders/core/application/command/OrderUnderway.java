package nl.hu.inno.orders.core.application.command;

import java.util.UUID;

public record OrderUnderway(UUID id, UUID delivery) {
}

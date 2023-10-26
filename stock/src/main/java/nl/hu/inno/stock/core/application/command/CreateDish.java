package nl.hu.inno.stock.core.application.command;

import java.util.List;
import java.util.UUID;

public record CreateDish(String name, List<UUID> ingredientsIds) {
}

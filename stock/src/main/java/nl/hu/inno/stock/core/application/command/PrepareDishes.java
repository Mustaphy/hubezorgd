package nl.hu.inno.stock.core.application.command;

import nl.hu.inno.stock.infrastructure.dto.OrderedDishDTO;

import java.util.List;
import java.util.UUID;

public record PrepareDishes(UUID order, List<OrderedDishDTO> orderedDishes) {
}

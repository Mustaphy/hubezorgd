package nl.hu.inno.stock.core.application.command;

import nl.hu.inno.stock.infrastructure.dto.OrderedDishDto;

import java.util.List;
import java.util.UUID;

public record PrepareDishes(UUID order, List<OrderedDishDto> orderedDishes) {
}

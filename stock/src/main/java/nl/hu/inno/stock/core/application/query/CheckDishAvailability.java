package nl.hu.inno.stock.core.application.query;

import nl.hu.inno.stock.infrastructure.dto.OrderedDishDto;

import java.util.List;

public record CheckDishAvailability(List<OrderedDishDto> dishes) {
}

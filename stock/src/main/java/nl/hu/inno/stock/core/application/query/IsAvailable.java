package nl.hu.inno.stock.core.application.query;

import nl.hu.inno.stock.infrastructure.dto.OrderedDishDTO;

import java.util.List;

public record IsAvailable(List<OrderedDishDTO> dishes) {
}

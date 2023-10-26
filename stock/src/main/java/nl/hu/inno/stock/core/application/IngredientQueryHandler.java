package nl.hu.inno.stock.core.application;

import nl.hu.inno.stock.core.data.storage.IngredientRepository;
import nl.hu.inno.stock.core.application.query.GetIngredientById;
import nl.hu.inno.stock.core.application.query.GetIngredients;
import nl.hu.inno.stock.core.domain.exception.IngredientNotFoundException;
import nl.hu.inno.stock.infrastructure.dto.IngredientDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientQueryHandler {
    private final IngredientRepository repository;

    public IngredientQueryHandler(IngredientRepository repository) {
        this.repository = repository;
    }

    public List<IngredientDto> handle(GetIngredients query) {
        return this.repository
                .findAll()
                .stream()
                .map(IngredientDto::toDto)
                .toList();
    }

    public IngredientDto handle(GetIngredientById query) {
        return IngredientDto
                .toDto(this.repository
                .findById(query.id())
                .orElseThrow(() -> new IngredientNotFoundException(String.format("Ingredient with id '%s' could not be found.", query.id()))));
    }
}

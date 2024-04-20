package nl.hu.inno.stock.core.application;

import jakarta.transaction.Transactional;
import nl.hu.inno.stock.core.data.storage.IngredientRepository;
import nl.hu.inno.stock.core.application.query.GetIngredientById;
import nl.hu.inno.stock.core.application.query.GetIngredients;
import nl.hu.inno.stock.core.domain.exception.IngredientNotFoundException;
import nl.hu.inno.stock.infrastructure.dto.IngredientDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class IngredientQueryHandler {
    private final IngredientRepository repository;

    public IngredientQueryHandler(IngredientRepository repository) {
        this.repository = repository;
    }

    public List<IngredientDTO> handle(GetIngredients query) {
        return this.repository
                .findAll()
                .stream()
                .map(IngredientDTO::toDTO)
                .toList();
    }

    public IngredientDTO handle(GetIngredientById query) {
        return IngredientDTO
                .toDTO(this.repository
                .findById(query.id())
                .orElseThrow(() -> new IngredientNotFoundException(String.format("Ingredient with id '%s' could not be found.", query.id()))));
    }
}

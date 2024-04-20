package nl.hu.inno.stock.core.application;

import jakarta.transaction.Transactional;
import nl.hu.inno.stock.core.data.storage.IngredientRepository;
import nl.hu.inno.stock.core.domain.Ingredient;
import nl.hu.inno.stock.core.application.command.CreateIngredient;
import nl.hu.inno.stock.core.data.IngredientEventPublisher;
import nl.hu.inno.stock.core.domain.event.IngredientEvent;
import nl.hu.inno.stock.infrastructure.dto.IngredientDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class IngredientCommandHandler {
    private final IngredientRepository repository;
    private final IngredientEventPublisher eventPublisher;

    public IngredientCommandHandler(IngredientRepository repository, IngredientEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public IngredientDTO handle(CreateIngredient command) {
        Ingredient ingredient = Ingredient.create(command.name(), command.vegetarian(), command.nrInStock());

        this.publishEventsAndSave(ingredient);

        return IngredientDTO.toDTO(ingredient);
    }

    private void publishEventsAndSave(Ingredient ingredient) {
        List<IngredientEvent> events = ingredient.listEvents();
        events.forEach(this.eventPublisher::publish);
        ingredient.clearEvents();

        this.repository.save(ingredient);
    }
}

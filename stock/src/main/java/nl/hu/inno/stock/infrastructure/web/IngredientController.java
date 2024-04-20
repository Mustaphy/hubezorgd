package nl.hu.inno.stock.infrastructure.web;

import nl.hu.inno.stock.core.application.IngredientCommandHandler;
import nl.hu.inno.stock.core.application.IngredientQueryHandler;
import nl.hu.inno.stock.core.application.command.CreateIngredient;
import nl.hu.inno.stock.core.application.query.GetIngredientById;
import nl.hu.inno.stock.core.application.query.GetIngredients;
import nl.hu.inno.stock.core.domain.Ingredient;
import nl.hu.inno.stock.infrastructure.dto.IngredientDto;
import nl.hu.inno.stock.infrastructure.web.request.CreateIngredientRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/stock/ingredients")
public class IngredientController {
    private final IngredientCommandHandler commandHandler;
    private final IngredientQueryHandler queryHandler;

    public IngredientController(IngredientCommandHandler commandHandler, IngredientQueryHandler queryHandler) {
        this.commandHandler = commandHandler;
        this.queryHandler = queryHandler;
    }

    @PostMapping
    public IngredientDto createIngredient(@RequestBody CreateIngredientRequest body) {
        return this.commandHandler.handle(new CreateIngredient(body.name, body.vegetarian, body.nrInStock));
    }

    @GetMapping
    public List<IngredientDto> getIngredients() {
        return this.queryHandler.handle(new GetIngredients());
    }

    @GetMapping("/{id}")
    public IngredientDto getIngredientById(@PathVariable("id") UUID id) {
        return this.queryHandler.handle(new GetIngredientById(id));
    }
}

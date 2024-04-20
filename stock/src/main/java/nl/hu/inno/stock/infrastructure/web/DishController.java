package nl.hu.inno.stock.infrastructure.web;

import nl.hu.inno.common.security.User;
import nl.hu.inno.stock.core.application.DishCommandHandler;
import nl.hu.inno.stock.core.application.DishQueryHandler;
import nl.hu.inno.stock.core.application.command.CreateDish;
import nl.hu.inno.stock.core.application.command.PostDishReview;
import nl.hu.inno.stock.core.application.query.IsAvailable;
import nl.hu.inno.stock.core.application.query.GetDishes;
import nl.hu.inno.stock.core.application.query.GetReviewsForDish;
import nl.hu.inno.stock.infrastructure.dto.DishDTO;
import nl.hu.inno.stock.infrastructure.dto.DishReviewDTO;
import nl.hu.inno.stock.infrastructure.dto.OrderedDishDTO;
import nl.hu.inno.stock.infrastructure.web.request.CreateDishRequest;
import nl.hu.inno.stock.core.application.query.GetDishById;
import nl.hu.inno.stock.infrastructure.web.request.PostReviewRequest;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/stock/dishes")
public class DishController {
    private final DishCommandHandler commandHandler;
    private final DishQueryHandler queryHandler;

    public DishController(DishCommandHandler commandHandler, DishQueryHandler queryHandler) {
        this.commandHandler = commandHandler;
        this.queryHandler = queryHandler;
    }

    @PostMapping
    public DishDTO createDish(@RequestBody CreateDishRequest body) {
        return this.commandHandler.handle(new CreateDish(body.name, body.ingredients));
    }

    @GetMapping
    public List<DishDTO> getDishes() {
        return this.queryHandler.handle(new GetDishes());
    }

    @GetMapping("/{id}")
    public DishDTO getDishById(@PathVariable("id") UUID id) {
        return this.queryHandler.handle(new GetDishById(id));
    }

    @GetMapping("/{id}/reviews")
    public List<DishReviewDTO> getReviewsForDish(@PathVariable("id") UUID id) {
        return this.queryHandler.handle(new GetReviewsForDish(id));
    }

    @PostMapping("/{id}/reviews")
    public DishReviewDTO postDishReview(User user, @PathVariable("id") UUID id, @RequestBody PostReviewRequest body) {
        return this.commandHandler.handle(new PostDishReview(id, body.rating, body.description, user));
    }

    @GetMapping("/is-available")
    public boolean isAvailable(@RequestParam MultiValueMap<String, String> parameterMap) {
        List<OrderedDishDTO> orderedDishes = parameterMap.get("orderedDishes")
                .stream()
                .collect(Collectors.groupingBy(UUID::fromString, Collectors.counting()))
                .entrySet()
                .stream()
                .map(dish -> new OrderedDishDTO(dish.getKey(), dish.getValue().intValue()))
                .toList();

        return this.queryHandler.handle(new IsAvailable(orderedDishes));
    }
}

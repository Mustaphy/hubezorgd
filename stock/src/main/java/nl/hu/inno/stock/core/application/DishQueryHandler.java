package nl.hu.inno.stock.core.application;

import nl.hu.inno.stock.core.application.query.*;
import nl.hu.inno.stock.core.data.storage.DishRepository;
import nl.hu.inno.stock.core.data.storage.DishReviewRepository;
import nl.hu.inno.stock.core.domain.Dish;
import nl.hu.inno.stock.core.domain.DishReview;
import nl.hu.inno.stock.core.domain.exception.DishNotFoundException;
import nl.hu.inno.stock.infrastructure.dto.DishDto;
import nl.hu.inno.stock.infrastructure.dto.DishReviewDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DishQueryHandler {
    private final IngredientQueryHandler ingredientQueryHandler;
    private final DishRepository dishRepository;
    private final DishReviewRepository dishReviewRepository;

    public DishQueryHandler(IngredientQueryHandler ingredientQueryHandler, DishRepository dishRepository, DishReviewRepository dishReviewRepository) {
        this.ingredientQueryHandler = ingredientQueryHandler;
        this.dishRepository = dishRepository;
        this.dishReviewRepository = dishReviewRepository;
    }

    public List<DishDto> handle(GetDishes query) {
        return this.dishRepository
                .findAll()
                .stream()
                .map(DishDto::toDto)
                .toList();
    }

    public DishDto handle(GetDishById query) {
        return DishDto.toDto(this.dishRepository
                .findById(query.id())
                .orElseThrow(() -> new DishNotFoundException(String.format("Dish with id '%s' could not be found.", query.id()))));
    }

    public boolean handle(CheckDishAvailability query) {
        return query.dishes()
                .stream()
                .allMatch(orderedDish -> {
                    Dish dish = this.dishRepository.findById(orderedDish.id())
                            .orElseThrow(() -> new DishNotFoundException(String.format("Dish with id '%s' could not be found.", orderedDish.id())));

                    return dish.getIngredients()
                            .stream()
                            .allMatch(ingredient -> ingredient.getNrInStock() >= orderedDish.nr());
                });
    }

    public List<DishReviewDto> handle(GetReviewsForDish query) {
        Dish dish = this.dishRepository.findById(query.id())
                .orElseThrow(() -> new DishNotFoundException(String.format("Dish with id '%s' could not be found.", query.id())));

        return this.dishReviewRepository.findDishReviewByDish(dish)
                .stream()
                .map(DishReviewDto::toDto)
                .toList();
    }
}

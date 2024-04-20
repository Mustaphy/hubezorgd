package nl.hu.inno.stock.core.application;

import jakarta.transaction.Transactional;
import nl.hu.inno.stock.core.application.query.*;
import nl.hu.inno.stock.core.data.storage.DishRepository;
import nl.hu.inno.stock.core.data.storage.DishReviewRepository;
import nl.hu.inno.stock.core.domain.Dish;
import nl.hu.inno.stock.core.domain.exception.DishNotFoundException;
import nl.hu.inno.stock.infrastructure.dto.DishDTO;
import nl.hu.inno.stock.infrastructure.dto.DishReviewDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class DishQueryHandler {
    private final DishRepository dishRepository;
    private final DishReviewRepository dishReviewRepository;

    public DishQueryHandler(DishRepository dishRepository, DishReviewRepository dishReviewRepository) {
        this.dishRepository = dishRepository;
        this.dishReviewRepository = dishReviewRepository;
    }

    public List<DishDTO> handle(GetDishes query) {
        return this.dishRepository
                .findAll()
                .stream()
                .map(DishDTO::toDTO)
                .toList();
    }

    public DishDTO handle(GetDishById query) {
        return DishDTO
                .toDTO(this.dishRepository
                .findById(query.id())
                .orElseThrow(() -> new DishNotFoundException(String.format("Dish with id '%s' could not be found.", query.id()))));
    }

    public boolean handle(IsAvailable query) {
        return query
                .dishes()
                .stream()
                .allMatch(orderedDish -> {
                    Dish dish = this.dishRepository
                            .findById(orderedDish.id())
                            .orElseThrow(() -> new DishNotFoundException(String.format("Dish with id '%s' could not be found.", orderedDish.id())));

                    return dish
                            .getIngredients()
                            .stream()
                            .allMatch(ingredient -> ingredient.getNrInStock() >= orderedDish.nr());
                });
    }

    public List<DishReviewDTO> handle(GetReviewsForDish query) {
        Dish dish = this.dishRepository
                .findById(query.id())
                .orElseThrow(() -> new DishNotFoundException(String.format("Dish with id '%s' could not be found.", query.id())));

        return this.dishReviewRepository.findDishReviewByDish(dish)
                .stream()
                .map(DishReviewDTO::toDTO)
                .toList();
    }
}

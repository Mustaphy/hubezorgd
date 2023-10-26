package nl.hu.inno.stock.core.data.storage;

import nl.hu.inno.stock.core.domain.Dish;
import nl.hu.inno.stock.core.domain.DishReview;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.UUID;

public interface DishReviewRepository extends MongoRepository<DishReview, UUID> {
    List<DishReview> findDishReviewByDish(Dish dish);
}

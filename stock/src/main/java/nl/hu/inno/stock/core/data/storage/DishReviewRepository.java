package nl.hu.inno.stock.core.data.storage;

import nl.hu.inno.stock.core.domain.Dish;
import nl.hu.inno.stock.core.domain.DishReview;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface DishReviewRepository extends JpaRepository<DishReview, UUID> {
    List<DishReview> findDishReviewByDish(Dish dish);
}

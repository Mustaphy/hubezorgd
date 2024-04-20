package nl.hu.inno.stock.core.data.storage;

import nl.hu.inno.stock.core.domain.Dish;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DishRepository extends JpaRepository<Dish, UUID> {

}

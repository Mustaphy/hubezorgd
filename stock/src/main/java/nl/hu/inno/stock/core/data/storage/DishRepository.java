package nl.hu.inno.stock.core.data.storage;

import nl.hu.inno.stock.core.domain.Dish;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface DishRepository extends MongoRepository<Dish, UUID> {

}

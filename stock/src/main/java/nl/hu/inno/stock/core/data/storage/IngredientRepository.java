package nl.hu.inno.stock.core.data.storage;

import nl.hu.inno.stock.core.domain.Ingredient;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface IngredientRepository extends MongoRepository<Ingredient, UUID> {
}

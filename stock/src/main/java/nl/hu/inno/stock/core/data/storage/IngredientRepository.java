package nl.hu.inno.stock.core.data.storage;

import nl.hu.inno.stock.core.domain.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IngredientRepository extends JpaRepository<Ingredient, UUID> {
}

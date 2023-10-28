package nl.hu.inno.delivery.core.data.storage;

import nl.hu.inno.delivery.core.domain.Delivery;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DeliveryRepository extends MongoRepository<Delivery, UUID> {
    List<Delivery> findDeliveriesByOrder_Username(String username);
    Optional<Delivery> findDeliveryByIdAndOrder_Username(UUID id, String username);
}

package nl.hu.inno.delivery.core.data.storage;

import nl.hu.inno.delivery.core.domain.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DeliveryRepository extends JpaRepository<Delivery, UUID> {
    List<Delivery> findDeliveriesByOrder_Username(String username);
    Optional<Delivery> findDeliveryByDeliveryIdAndOrder_Username(UUID id, String username);
}

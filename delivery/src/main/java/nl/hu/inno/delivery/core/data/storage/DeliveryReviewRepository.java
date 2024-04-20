package nl.hu.inno.delivery.core.data.storage;

import nl.hu.inno.delivery.core.domain.Delivery;
import nl.hu.inno.delivery.core.domain.DeliveryReview;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface DeliveryReviewRepository extends JpaRepository<DeliveryReview, UUID> {
    List<DeliveryReview> findByDelivery(Delivery delivery);
}

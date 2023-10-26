package nl.hu.inno.delivery.core.data.storage;

import nl.hu.inno.delivery.core.domain.Delivery;
import nl.hu.inno.delivery.core.domain.DeliveryReview;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.UUID;

public interface DeliveryReviewRepository extends MongoRepository<DeliveryReview, UUID> {
    List<DeliveryReview> findByDelivery(Delivery delivery);
}

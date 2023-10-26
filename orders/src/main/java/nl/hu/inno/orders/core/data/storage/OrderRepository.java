package nl.hu.inno.orders.core.data.storage;

import nl.hu.inno.orders.core.domain.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface OrderRepository extends MongoRepository<Order, UUID> {
}

package nl.hu.inno.orders.core.data.storage;

import nl.hu.inno.common.security.User;
import nl.hu.inno.orders.core.domain.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderRepository extends MongoRepository<Order, UUID> {
    List<Order> findAllByUser(User user);
    Optional<Order> findByIdAndUser(UUID id, User user);
}

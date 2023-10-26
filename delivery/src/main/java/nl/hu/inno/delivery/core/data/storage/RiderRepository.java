package nl.hu.inno.delivery.core.data.storage;

import nl.hu.inno.delivery.core.domain.Rider;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface RiderRepository extends MongoRepository<Rider, UUID> {
}

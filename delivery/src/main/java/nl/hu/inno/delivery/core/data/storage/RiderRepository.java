package nl.hu.inno.delivery.core.data.storage;

import nl.hu.inno.delivery.core.domain.Rider;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RiderRepository extends JpaRepository<Rider, UUID> {
}

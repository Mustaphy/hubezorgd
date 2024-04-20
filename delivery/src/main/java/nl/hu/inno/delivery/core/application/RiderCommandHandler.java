package nl.hu.inno.delivery.core.application;

import nl.hu.inno.delivery.core.application.command.CreateRider;
import nl.hu.inno.delivery.core.data.storage.RiderRepository;
import nl.hu.inno.delivery.core.domain.Rider;
import nl.hu.inno.delivery.infrastructure.dto.RiderDTO;
import org.springframework.stereotype.Service;
@Service
public class RiderCommandHandler {
    private final RiderRepository riderRepository;

    public RiderCommandHandler(RiderRepository riderRepository) {
        this.riderRepository = riderRepository;
    }

    public RiderDTO handle(CreateRider command) {
        Rider rider = Rider.create(command.name());

        return RiderDTO.toDTO(this.riderRepository.save(rider));
    }
}

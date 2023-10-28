package nl.hu.inno.delivery.core.application;

import nl.hu.inno.delivery.core.application.command.CreateRider;
import nl.hu.inno.delivery.core.data.storage.RiderRepository;
import nl.hu.inno.delivery.core.domain.Rider;
import nl.hu.inno.delivery.infrastructure.dto.RiderDto;
import org.springframework.stereotype.Service;
@Service
public class RiderCommandHandler {
    private final RiderRepository riderRepository;

    public RiderCommandHandler(RiderRepository riderRepository) {
        this.riderRepository = riderRepository;
    }

    public RiderDto handle(CreateRider command) {
        Rider rider = Rider.create(command.name());

        return RiderDto.toDto(this.riderRepository.save(rider));
    }
}

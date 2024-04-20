package nl.hu.inno.delivery.core.application;

import nl.hu.inno.delivery.core.application.query.GetRiderById;
import nl.hu.inno.delivery.core.application.query.GetRiders;
import nl.hu.inno.delivery.core.data.storage.RiderRepository;
import nl.hu.inno.delivery.core.domain.exception.RiderNotFoundException;
import nl.hu.inno.delivery.infrastructure.dto.RiderDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RiderQueryHandler {
    private final RiderRepository riderRepository;

    public RiderQueryHandler(RiderRepository riderRepository) {
        this.riderRepository = riderRepository;
    }

    public List<RiderDTO> handle(GetRiders query) {
        return this.riderRepository
                .findAll()
                .stream()
                .map(RiderDTO::toDTO)
                .toList();
    }

    public RiderDTO handle(GetRiderById query) {
        return RiderDTO
                .toDTO(this.riderRepository
                .findById(query.id())
                .orElseThrow(() -> new RiderNotFoundException(String.format("Rider with id '%s' could not be found.", query.id()))));
    }
}

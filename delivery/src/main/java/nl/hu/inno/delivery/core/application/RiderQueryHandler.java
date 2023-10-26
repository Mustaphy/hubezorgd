package nl.hu.inno.delivery.core.application;

import nl.hu.inno.delivery.core.application.query.GetRiderById;
import nl.hu.inno.delivery.core.application.query.GetRiders;
import nl.hu.inno.delivery.core.data.storage.RiderRepository;
import nl.hu.inno.delivery.core.domain.exception.RiderNotFoundException;
import nl.hu.inno.delivery.infrastructure.dto.RiderDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RiderQueryHandler {
    private final RiderRepository riderRepository;

    public RiderQueryHandler(RiderRepository riderRepository) {
        this.riderRepository = riderRepository;
    }

    public List<RiderDto> handle(GetRiders query) {
        return this.riderRepository
                .findAll()
                .stream()
                .map(RiderDto::toDto)
                .toList();
    }

    public RiderDto handle(GetRiderById query) {
        return RiderDto.toDto(this.riderRepository
                .findById(query.id())
                .orElseThrow(() -> new RiderNotFoundException(String.format("Rider with id '%s' could not be found.", query.id()))));
    }
}

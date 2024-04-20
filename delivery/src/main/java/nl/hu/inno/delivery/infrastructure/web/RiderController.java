package nl.hu.inno.delivery.infrastructure.web;

import nl.hu.inno.delivery.core.application.RiderCommandHandler;
import nl.hu.inno.delivery.core.application.RiderQueryHandler;
import nl.hu.inno.delivery.core.application.command.CreateRider;
import nl.hu.inno.delivery.core.application.query.GetRiders;
import nl.hu.inno.delivery.core.application.query.GetRiderById;
import nl.hu.inno.delivery.infrastructure.dto.RiderDTO;
import nl.hu.inno.delivery.infrastructure.web.request.CreateRiderRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/rider")
public class RiderController {
    private final RiderCommandHandler riderCommandHandler;
    private final RiderQueryHandler riderQueryHandler;

    public RiderController(RiderCommandHandler riderCommandHandler, RiderQueryHandler riderQueryHandler) {
        this.riderCommandHandler = riderCommandHandler;
        this.riderQueryHandler = riderQueryHandler;
    }

    @GetMapping
    public List<RiderDTO> handle() {
        return this.riderQueryHandler.handle(new GetRiders());
    }

    @GetMapping("/{id}")
    public RiderDTO handle(@PathVariable("id") UUID id) {
        return this.riderQueryHandler.handle(new GetRiderById(id));
    }

    @PostMapping
    public RiderDTO handle(@RequestBody CreateRiderRequest body) {
        return this.riderCommandHandler.handle(new CreateRider(body.name));
    }
}

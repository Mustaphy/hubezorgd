package nl.hu.inno.delivery.infrastructure.web;

import nl.hu.inno.common.security.User;
import nl.hu.inno.delivery.core.application.DeliveryCommandHandler;
import nl.hu.inno.delivery.core.application.DeliveryQueryHandler;
import nl.hu.inno.delivery.core.application.command.PostDeliveryReview;
import nl.hu.inno.delivery.core.application.query.GetDeliveries;
import nl.hu.inno.delivery.core.application.query.GetDeliveryById;
import nl.hu.inno.delivery.core.application.query.GetReviewsForDelivery;
import nl.hu.inno.delivery.infrastructure.dto.DeliveryDTO;
import nl.hu.inno.delivery.infrastructure.dto.DeliveryReviewDTO;
import nl.hu.inno.delivery.infrastructure.web.request.PostReviewRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/delivery")
public class DeliveryController {
    private final DeliveryQueryHandler deliveryQueryHandler;
    private final DeliveryCommandHandler deliveryCommandHandler;

    public DeliveryController(DeliveryQueryHandler deliveryQueryHandler, DeliveryCommandHandler deliveryCommandHandler) {
        this.deliveryQueryHandler = deliveryQueryHandler;
        this.deliveryCommandHandler = deliveryCommandHandler;
    }

    @GetMapping
    public List<DeliveryDTO> getDeliveries(User user) {
        return this.deliveryQueryHandler.handle(new GetDeliveries(user));
    }

    @GetMapping("/{id}")
    public DeliveryDTO getDeliveryById(User user, @PathVariable("id") UUID id) {
        return this.deliveryQueryHandler.handle(new GetDeliveryById(user, id));
    }

    @GetMapping("/{id}/reviews")
    public List<DeliveryReviewDTO> getReviewsForDelivery(@PathVariable("id") UUID id) {
        return this.deliveryQueryHandler.handle(new GetReviewsForDelivery(id));
    }

    @PostMapping("/{id}/reviews")
    public DeliveryReviewDTO postDeliveryReview(User user, @PathVariable("id") UUID id, @RequestBody PostReviewRequest body) {
        return this.deliveryCommandHandler.handle(new PostDeliveryReview(id, body.rating, body.description, user));
    }
}

package nl.hu.inno.delivery.infrastructure.web;

import nl.hu.inno.common.security.User;
import nl.hu.inno.delivery.core.application.DeliveryCommandHandler;
import nl.hu.inno.delivery.core.application.DeliveryQueryHandler;
import nl.hu.inno.delivery.core.application.command.PostDeliveryReview;
import nl.hu.inno.delivery.core.application.query.GetDeliveries;
import nl.hu.inno.delivery.core.application.query.GetDeliveryById;
import nl.hu.inno.delivery.core.application.query.GetDeliveryReviewsForDelivery;
import nl.hu.inno.delivery.infrastructure.dto.DeliveryDto;
import nl.hu.inno.delivery.infrastructure.dto.DeliveryReviewDto;
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
    public List<DeliveryDto> getDeliveries(User user) {
        return this.deliveryQueryHandler.handle(new GetDeliveries(user));
    }

    @GetMapping("/{id}")
    public DeliveryDto getDeliveryById(User user, @PathVariable("id") UUID id) {
        return this.deliveryQueryHandler.handle(new GetDeliveryById(user, id));
    }

    @GetMapping("/{id}/reviews")
    public List<DeliveryReviewDto> getReviewsForDelivery(@PathVariable("id") UUID id) {
        return this.deliveryQueryHandler.handle(new GetDeliveryReviewsForDelivery(id));
    }

    @PostMapping("/{id}/reviews")
    public DeliveryReviewDto postReview(User user, @PathVariable("id") UUID id, @RequestBody PostReviewRequest body) {
        return this.deliveryCommandHandler.handle(new PostDeliveryReview(id, body.rating, body.description, user));
    }
}

package nl.hu.inno.delivery.infrastructure.dto;

import nl.hu.inno.delivery.core.domain.Delivery;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

public record DeliveryDto(UUID id, String timeLeft, String riderName, String orderLink) {
    public static DeliveryDto toDto(Delivery delivery) {
        return new DeliveryDto(delivery.getId(), DeliveryDto.getTimeLeft(delivery.getEstimatedDelivery()), delivery.getRider().getName(), String.format("http://localhost:8080/orders/%s", delivery.getOrder().id()));
    }

    public static String getTimeLeft(LocalDateTime estimatedDelivery) {
        if (LocalDateTime.now().isAfter(estimatedDelivery))
            return "00:00";

        String minutes = ChronoUnit.MINUTES.between(LocalDateTime.now(), estimatedDelivery) + "";
        String seconds = ChronoUnit.SECONDS.between(LocalDateTime.now(), estimatedDelivery) + "";

        if (minutes.length() == 1) minutes = String.format("0%s", minutes);
        if (seconds.length() == 1) seconds = String.format("0%s", seconds);

        return String.format("%s:%s", minutes, seconds);
    }
}

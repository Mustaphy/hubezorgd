package nl.hu.inno.delivery.infrastructure.web.handler;

import java.time.LocalDateTime;

public record ApiError(Integer status, String reasonPhrase, String path, String message, LocalDateTime timestamp) {
}

package nl.hu.inno.stock.infrastructure.web.handler;

import java.time.LocalDateTime;

public record ApiError(Integer status, String reasonPhrase, String path, String message, LocalDateTime timestamp) {
}

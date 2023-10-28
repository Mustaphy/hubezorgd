package nl.hu.inno.delivery.infrastructure.web.handler;

import nl.hu.inno.delivery.core.domain.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class DeliveryExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler({
            InvalidRatingException.class,
            DeliveryNotCompletedException.class
    })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleBadRequestException(RuntimeException exception) {
        return this.createApiError(HttpStatus.BAD_REQUEST, exception);
    }

    @ExceptionHandler({
            DeliveryNotFoundException.class,
            RiderNotFoundException.class,
            HttpClientErrorException.NotFound.class
    })
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleNotFoundException(RuntimeException exception) {
        return this.createApiError(HttpStatus.NOT_FOUND, exception);
    }

    @ExceptionHandler({
            OrdersServiceOfflineException.class
    })
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public ErrorResponse handleServiceUnavailableException(RuntimeException exception) {
        return this.createApiError(HttpStatus.SERVICE_UNAVAILABLE, exception);
    }

    private ErrorResponse createApiError(HttpStatus status, RuntimeException exception) {
        // RFC 7807 Specification
        return ErrorResponse
                .builder(exception, status, exception.getMessage())
                .build();
    }
}

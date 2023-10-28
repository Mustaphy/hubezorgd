package nl.hu.inno.orders.infrastructure.web.handler;

import nl.hu.inno.orders.core.domain.exception.InvalidIntervalException;
import nl.hu.inno.orders.core.domain.exception.OrderNotFoundException;
import nl.hu.inno.orders.core.domain.exception.OutOfStockException;
import nl.hu.inno.orders.core.domain.exception.StockServiceOfflineException;
import org.springframework.http.HttpStatus;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class OrdersExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler({
            InvalidIntervalException.class,
    })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleBadRequestException(RuntimeException exception) {
        return this.createApiError(HttpStatus.BAD_REQUEST, exception);
    }

    @ExceptionHandler({
            OrderNotFoundException.class,
            HttpClientErrorException.NotFound.class
    })
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleNotFoundException(RuntimeException exception) {
        return this.createApiError(HttpStatus.NOT_FOUND, exception);
    }

    @ExceptionHandler({
            OutOfStockException.class
    })
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleConflictException(RuntimeException exception) {
        return this.createApiError(HttpStatus.CONFLICT, exception);
    }

    @ExceptionHandler({
            StockServiceOfflineException.class
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

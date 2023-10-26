package nl.hu.inno.orders.infrastructure.web.handler;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import nl.hu.inno.orders.core.domain.exception.InvalidIntervalException;
import nl.hu.inno.orders.core.domain.exception.OrderNotFoundException;
import nl.hu.inno.orders.core.domain.exception.OutOfStockException;
import nl.hu.inno.orders.core.domain.exception.StockServiceOfflineException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Arrays;

@RestControllerAdvice
public class OrdersExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler({
            InvalidIntervalException.class,
    })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleBadRequestException(RuntimeException exception, HttpServletRequest request) {
        return this.createApiError(HttpStatus.BAD_REQUEST, exception.getMessage(), request.getRequestURI());
    }

    @ExceptionHandler({
            OrderNotFoundException.class
    })
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> handleNotFoundException(RuntimeException exception, HttpServletRequest request) {
        return this.createApiError(HttpStatus.NOT_FOUND, exception.getMessage(), request.getRequestURI());
    }

    @ExceptionHandler({
            OutOfStockException.class
    })
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<Object> handleConflictException(RuntimeException exception, HttpServletRequest request) {
        return this.createApiError(HttpStatus.CONFLICT, exception.getMessage(), request.getRequestURI());
    }

    @ExceptionHandler({
            StockServiceOfflineException.class
    })
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public ResponseEntity<Object> handleServiceUnavailableException(RuntimeException exception, HttpServletRequest request) {
        return this.createApiError(HttpStatus.SERVICE_UNAVAILABLE, exception.getMessage(), request.getRequestURI());
    }

    private ResponseEntity<Object> createApiError(HttpStatus status, String message, String path) {
        ApiError apiResponse = new ApiError(status.value(), status.getReasonPhrase(), path, message, LocalDateTime.now());

        return new ResponseEntity<>(apiResponse, status);
    }
}

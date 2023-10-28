package nl.hu.inno.stock.infrastructure.web.handler;

import nl.hu.inno.stock.core.domain.exception.*;
import org.springframework.http.*;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class StockExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler({
            NoIngredientsException.class,
            InvalidRatingException.class
    })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleBadRequestException(RuntimeException exception) {
        return this.createApiError(HttpStatus.BAD_REQUEST, exception);
    }

    @ExceptionHandler({
            DishNotFoundException.class,
            IngredientNotFoundException.class,
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

    private ErrorResponse createApiError(HttpStatus status, RuntimeException exception) {
        // RFC 7807 Specification
        return ErrorResponse
                .builder(exception, status, exception.getMessage())
                .build();
    }
}

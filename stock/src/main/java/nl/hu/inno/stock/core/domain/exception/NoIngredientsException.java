package nl.hu.inno.stock.core.domain.exception;

public class NoIngredientsException extends RuntimeException {
    public NoIngredientsException(String message) {
        super(message);
    }
}

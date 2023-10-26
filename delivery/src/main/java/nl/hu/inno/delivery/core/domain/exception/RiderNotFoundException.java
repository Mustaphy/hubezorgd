package nl.hu.inno.delivery.core.domain.exception;

public class RiderNotFoundException extends RuntimeException {
    public RiderNotFoundException(String message) {
        super(message);
    }
}

package nl.hu.inno.delivery.core.domain.exception;

public class NoAvailableRidersException extends RuntimeException {
    public NoAvailableRidersException(String message) {
        super(message);
    }
}

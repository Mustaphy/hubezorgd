package nl.hu.inno.delivery.core.domain.exception;

public class DeliveryNotCompletedException extends RuntimeException {
    public DeliveryNotCompletedException(String message) {
        super(message);
    }
}

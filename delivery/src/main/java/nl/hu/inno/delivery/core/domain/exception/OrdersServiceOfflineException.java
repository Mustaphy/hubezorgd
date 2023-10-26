package nl.hu.inno.delivery.core.domain.exception;

public class OrdersServiceOfflineException extends RuntimeException {
    public OrdersServiceOfflineException(String message) {
        super(message);
    }
}

package nl.hu.inno.orders.core.domain.exception;

public class StockServiceOfflineException extends RuntimeException {
    public StockServiceOfflineException(String message) {
        super(message);
    }
}

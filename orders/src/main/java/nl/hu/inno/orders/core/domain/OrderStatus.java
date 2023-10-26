package nl.hu.inno.orders.core.domain;

public enum OrderStatus {
    RECEIVED,
    READY_FOR_DELIVERY,
    UNDERWAY,
    DELIVERED,
    DISPUTED;

    public OrderStatus next() {
        return switch (this) {
            case RECEIVED -> READY_FOR_DELIVERY;
            case READY_FOR_DELIVERY -> UNDERWAY;
            case UNDERWAY -> DELIVERED;
            case DELIVERED -> DELIVERED;
            case DISPUTED -> DISPUTED;
        };
    }
}

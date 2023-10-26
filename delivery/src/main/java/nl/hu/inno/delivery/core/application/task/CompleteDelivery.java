package nl.hu.inno.delivery.core.application.task;

import nl.hu.inno.delivery.core.application.DeliveryCommandHandler;

import java.util.UUID;

public record CompleteDelivery(DeliveryCommandHandler commandHandler, UUID id) implements Runnable {
    @Override
    public void run() {
        this.commandHandler.handle(this);
    }
}

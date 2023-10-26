package nl.hu.inno.orders.core.domain;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import nl.hu.inno.orders.core.domain.event.OrderReadyForDeliveryEvent;
import nl.hu.inno.orders.core.domain.info.DeliveryInfo;
import nl.hu.inno.orders.core.domain.info.DishInfo;
import nl.hu.inno.orders.core.domain.event.OrderEvent;
import nl.hu.inno.orders.core.domain.event.OrderPlacedEvent;
import nl.hu.inno.common.security.User;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Order {
    @Id
    private UUID id;
    private User user;
    private LocalDateTime orderDate;
    private OrderStatus status;
    private List<OrderedDish> orderedDishes = new ArrayList<>();
    private DeliveryInfo delivery;
    @Transient
    private List<OrderEvent> events = new ArrayList<>();

    private Order() { }

    private Order(User u, LocalDateTime orderDate) {
        this.id = UUID.randomUUID();
        this.user = u;
        this.orderDate = orderDate;
        this.status = OrderStatus.RECEIVED;
    }

    public static Order create(User u, LocalDateTime orderDate, List<DishInfo> dishes) {
        Order order = new Order(u, orderDate);

        order.addDishes(dishes);
        order.events.add(new OrderPlacedEvent(order.id, order.orderedDishes));

        return order;
    }

    public UUID getId() {
        return id;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public DeliveryInfo getDelivery() {
        return delivery;
    }

    public List<OrderedDish> getOrderedDishes() {
        return this.orderedDishes;
    }

    public void readyForDelivery() {
        this.status = OrderStatus.READY_FOR_DELIVERY;
        this.events.add(new OrderReadyForDeliveryEvent(this.id, this.user.getUsername()));
    }

    public void underway(DeliveryInfo delivery) {
        this.status = OrderStatus.UNDERWAY;
        this.delivery = delivery;
    }

    public void delivered() {
        this.status = OrderStatus.DELIVERED;
    }

    public void addDishes(List<DishInfo> dishes) {
        Map<DishInfo, Long> ordered = dishes
                .stream()
                .collect(Collectors.groupingBy(e -> e, Collectors.counting()));

        ordered.forEach((key, value) -> orderedDishes.add(OrderedDish.create(this, key, value.intValue())));
    }

    public List<OrderEvent> listEvents() {
        return events;
    }

    public void clearEvents() {
        this.events.clear();
    }
}

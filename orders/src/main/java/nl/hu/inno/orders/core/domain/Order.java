package nl.hu.inno.orders.core.domain;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import jakarta.persistence.*;
import nl.hu.inno.orders.core.domain.event.OrderReadyForDeliveryEvent;
import nl.hu.inno.orders.core.domain.info.DeliveryInfo;
import nl.hu.inno.orders.core.domain.info.DishInfo;
import nl.hu.inno.orders.core.domain.event.OrderEvent;
import nl.hu.inno.orders.core.domain.event.OrderPlacedEvent;
import nl.hu.inno.common.security.User;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.springframework.data.annotation.Transient;

@Entity(name = "Orders")
public class Order {
    @Id
    private UUID id;
    @ManyToOne
    private User user;
    private LocalDateTime orderDate;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    @OneToMany(mappedBy = "id.order")
    @Cascade(CascadeType.PERSIST)
    private List<OrderedDish> orderedDishes = new ArrayList<>();
    @Embedded
    private DeliveryInfo delivery;
    @ElementCollection
    @Transient
    private List<OrderEvent> events = new ArrayList<>();

    protected Order() { }

    private Order(User user, LocalDateTime orderDate) {
        this.id = UUID.randomUUID();
        this.user = user;
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

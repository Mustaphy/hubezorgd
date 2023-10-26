package nl.hu.inno.orders.core.application;

import nl.hu.inno.orders.core.application.query.GetReport;
import nl.hu.inno.orders.core.data.storage.OrderRepository;
import nl.hu.inno.orders.core.domain.Order;
import nl.hu.inno.orders.core.domain.exception.InvalidIntervalException;
import nl.hu.inno.orders.infrastructure.dto.ReportDto;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReportQueryHandler {
    private final OrderRepository orderRepository;

    public ReportQueryHandler(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<ReportDto> handle(GetReport query) {
        return this.orderRepository
                .findAll()
                .stream()
                .collect(Collectors.groupingBy(order -> this.getIntervalGrouping(order, query.interval())))
                .entrySet()
                .stream()
                .map(entrySet -> ReportDto.toDto(entrySet.getKey(), entrySet.getValue().size()))
                .toList();
    }

    private String getIntervalGrouping(Order order, String interval) {
        LocalDateTime orderDate = order.getOrderDate();
        return switch (interval) {
            case "minute" -> String.format("%s-%s-%s %s:%s", orderDate.getYear(), dateFormat(orderDate.getMonthValue()), dateFormat(orderDate.getDayOfMonth()), dateFormat(orderDate.getHour()), dateFormat(orderDate.getMinute()));
            case "hour" -> String.format("%s-%s-%s %s:00", orderDate.getYear(), dateFormat(orderDate.getMonthValue()), dateFormat(orderDate.getDayOfMonth()), dateFormat(orderDate.getHour()));
            case "day" -> String.format("%s-%s-%s", orderDate.getYear(), dateFormat(orderDate.getMonthValue()), dateFormat(orderDate.getDayOfMonth()));
            case "week" -> String.format("%s W%s", dateFormat(orderDate.getYear()), orderDate.get(ChronoField.ALIGNED_WEEK_OF_YEAR));
            case "month" -> String.format("%s-%s", orderDate.getYear(), dateFormat(orderDate.getMonthValue()));
            case "year" -> String.format("%s", orderDate.getYear());
            default -> throw new InvalidIntervalException(String.format("Query parameter 'interval' with value '%s' is invalid. Interval must be minute, hour, day, week, month or year", interval));
        };
    }

    private String dateFormat(int number) {
        return number < 10 ? String.format("0%s", number) : String.valueOf(number);
    }
}

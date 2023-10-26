package nl.hu.inno.orders.infrastructure.dto;

import java.time.LocalDateTime;
import java.util.HashMap;

public record ReportDto(String date, int totalOrders) {
    public static ReportDto toDto(String date, int totalOrders) {
        return new ReportDto(date, totalOrders);
    }
}

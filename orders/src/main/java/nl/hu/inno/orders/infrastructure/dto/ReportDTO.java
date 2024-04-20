package nl.hu.inno.orders.infrastructure.dto;

public record ReportDTO(String date, int totalOrders) {
    public static ReportDTO toDTO(String date, int totalOrders) {
        return new ReportDTO(date, totalOrders);
    }
}

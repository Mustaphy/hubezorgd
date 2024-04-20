package nl.hu.inno.orders.infrastructure.web;

import nl.hu.inno.orders.core.application.ReportQueryHandler;
import nl.hu.inno.orders.core.application.query.GetReport;
import nl.hu.inno.orders.infrastructure.dto.ReportDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("orders/report")
public class ReportController {
    private final ReportQueryHandler reportQueryHandler;

    public ReportController(ReportQueryHandler reportQueryHandler) {
        this.reportQueryHandler = reportQueryHandler;
    }

    // POSSIBLE QUERY PARAMETERS
    // interval: minute, hour, day, week, month, year (default: day)
    @GetMapping
    public List<ReportDTO> getReport(@RequestParam Optional<String> interval) {
        return this.reportQueryHandler.handle(new GetReport(interval.orElse("day")));
    }
}

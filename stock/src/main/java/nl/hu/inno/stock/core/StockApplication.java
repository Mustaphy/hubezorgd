package nl.hu.inno.stock.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackages = {"nl.hu.inno.stock.core.data", "nl.hu.inno.common.security"})
@ComponentScan(basePackages = {"nl.hu.inno.stock", "nl.hu.inno.common"})
public class StockApplication {
    public static void main(String[] args) {
        SpringApplication.run(StockApplication.class, args);
    }
}

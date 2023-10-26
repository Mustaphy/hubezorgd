package nl.hu.inno.orders.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackages = {"nl.hu.inno.orders.core.data", "nl.hu.inno.common.security"})
@ComponentScan(basePackages = {"nl.hu.inno.orders", "nl.hu.inno.common"})
public class OrdersApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrdersApplication.class, args);
    }
}

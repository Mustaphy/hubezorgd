package nl.hu.inno.orders.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = { "nl.hu.inno.orders", "nl.hu.inno.common" })
@EntityScan(basePackages = { "nl.hu.inno.orders", "nl.hu.inno.common" })
@EnableJpaRepositories(basePackages = { "nl.hu.inno.orders", "nl.hu.inno.common" })
public class OrdersApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrdersApplication.class, args);
    }
}

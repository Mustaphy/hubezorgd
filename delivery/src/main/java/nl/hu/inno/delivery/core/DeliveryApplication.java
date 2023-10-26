package nl.hu.inno.delivery.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackages = {"nl.hu.inno.delivery.core.data", "nl.hu.inno.common.security"})
@ComponentScan(basePackages = {"nl.hu.inno.delivery", "nl.hu.inno.common"})
public class DeliveryApplication {
    public static void main(String[] args) {
        SpringApplication.run(DeliveryApplication.class, args);
    }
}

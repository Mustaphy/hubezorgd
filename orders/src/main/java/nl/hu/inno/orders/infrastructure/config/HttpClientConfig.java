package nl.hu.inno.orders.infrastructure.config;

import nl.hu.inno.orders.infrastructure.gateway.HttpStockGateway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class HttpClientConfig {
    @Value("${http-client.root-path.stock}")
    private String rootPath;

    @Bean
    public HttpStockGateway httpStockGateway() {
        return new HttpStockGateway(rootPath, restTemplate());
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}

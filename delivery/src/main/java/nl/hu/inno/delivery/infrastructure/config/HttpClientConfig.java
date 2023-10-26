package nl.hu.inno.delivery.infrastructure.config;

import nl.hu.inno.delivery.infrastructure.gateway.HttpOrdersGateway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class HttpClientConfig {
    @Value("${http-client.root-path.orders}")
    private String rootPath;

    @Bean
    public HttpOrdersGateway httpOrdersGateway() {
        return new HttpOrdersGateway(rootPath, restTemplate());
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}

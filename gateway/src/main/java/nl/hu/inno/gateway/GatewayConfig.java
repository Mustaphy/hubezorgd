package nl.hu.inno.gateway;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {
    @Value("${http-client.path.orders}")
    private String ordersPath;
    @Value("${http-client.path.stock}")
    private String stockPath;
    @Value("${http-client.path.delivery}")
    private String deliveryPath;

    @Bean
    public RouteLocator gatewayRouting(RouteLocatorBuilder routeLocatorBuilder) {
        return routeLocatorBuilder.routes()
                .route(route -> route.path("/orders/**").uri(this.ordersPath))
                .route(route -> route.path("/stock/dishes/**", "/stock/ingredients/**").uri(this.stockPath))
                .route(route -> route.path("/delivery/**", "/rider/**").uri(this.deliveryPath))
                .build();
    }
}

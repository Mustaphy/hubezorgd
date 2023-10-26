package nl.hu.inno.orders.infrastructure.gateway;

import nl.hu.inno.orders.core.domain.OrderedDish;
import nl.hu.inno.orders.core.domain.exception.StockServiceOfflineException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.rmi.ConnectException;
import java.util.List;

public class HttpStockGateway {
    private final String rootPath;
    private final RestTemplate client;

    public HttpStockGateway(String rootPath, RestTemplate client) {
        this.rootPath = rootPath;
        this.client = client;
    }

    public boolean checkStock(List<OrderedDish> orderedDishes) {
        StringBuilder queryParameters = new StringBuilder();
        orderedDishes.forEach(dish -> queryParameters.append(String.format("orderedDishes=%s&", dish.getDishId())));
        URI uri = URI.create(this.rootPath + "/stock/dishes/check-availability?" + queryParameters);

        Boolean result = null;
        try {
            result = this.client.getForObject(uri, Boolean.class);
        } catch (RestClientException e) {
            // Other exceptions are caught in the exception handler
            if (e.getCause() instanceof ConnectException)
                throw new StockServiceOfflineException("The stock service is currently offline");
        }

        return Boolean.TRUE.equals(result);
    }
}

package nl.hu.inno.delivery.infrastructure.gateway;

import nl.hu.inno.delivery.core.domain.exception.OrdersServiceOfflineException;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.rmi.ConnectException;
import java.util.UUID;

public class HttpOrdersGateway {
    private final String rootPath;
    private final RestTemplate client;

    public HttpOrdersGateway(String rootPath, RestTemplate client) {
        this.rootPath = rootPath;
        this.client = client;
    }

    public boolean isDelivered(UUID id) {
        URI uri = URI.create(String.format("%s/orders/%s/is-delivered", this.rootPath, id));

        Boolean result = null;
        try {
            result = this.client.getForObject(uri, Boolean.class);
        } catch (RestClientException e) {
            if (e.getCause() instanceof ConnectException) {
                throw new OrdersServiceOfflineException("The order service is currently offline");
            }
        }

        return Boolean.TRUE.equals(result);
    }
}

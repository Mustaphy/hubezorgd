package nl.hu.inno.orders.infrastructure.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.hu.inno.orders.infrastructure.messaging.RabbitMqEventPublisher;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Configuration
public class RabbitMqConfig {
    @Value("${spring.rabbitmq.host}")
    private String host;
    @Value("${spring.rabbitmq.port}")
    private int port;

    @Value("${messaging.queue.stock}")
    private String stockQueueName;
    @Value("${messaging.queue.orders}")
    private String ordersQueueName;
    @Value("${messaging.queue.delivery}")
    private String deliveryQueueName;

    @Bean
    public Queue stockQueue() {
        return QueueBuilder.durable(this.stockQueueName).build();
    }

    @Bean
    public Queue ordersQueue() {
        return QueueBuilder.durable(this.ordersQueueName).build();
    }

    @Bean
    public Queue deliveryQueue() {
        return QueueBuilder.durable(this.deliveryQueueName).build();
    }

    @Bean
    public RabbitMqEventPublisher eventPublisher(RabbitTemplate template) {
        return new RabbitMqEventPublisher(template, this.ordersQueueName);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(Jackson2JsonMessageConverter converter) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(connectionFactory());
        rabbitTemplate.setMessageConverter(converter);

        return rabbitTemplate;
    }

    @Bean
    public Jackson2JsonMessageConverter converter(Jackson2ObjectMapperBuilder builder) {
        ObjectMapper objectMapper = builder
                .createXmlMapper(false)
                .build();

        Jackson2JsonMessageConverter converter = new Jackson2JsonMessageConverter(objectMapper);

        converter.setAlwaysConvertToInferredType(true);

        return converter;
    }

    @Bean
    public ConnectionFactory connectionFactory() {
        return new CachingConnectionFactory(this.host, this.port);
    }
}

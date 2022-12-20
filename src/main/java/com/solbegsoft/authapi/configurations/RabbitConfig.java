package com.solbegsoft.authapi.configurations;


import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Rabbit Configuration
 */
@Configuration
public class RabbitConfig {

    /**
     * Queue to Auth API
     */
    public static final String QUEUE_AUTH_API_OUTPUT = "auth-api.queues.get-all.output";

    /**
     * Queue to Favorites API Output
     */
    public static final String QUEUE_FAVORITES_API_OUTPUT = "auth-api.queues.get-all.output";

    /**
     * Queue to Auth API Input
     */
    public static final String QUEUE_AUTH_API_INPUT = "auth-api.queues.get-all.input";

    /**
     * Queue to Auth API ERROR
     */
    public static final String QUEUE_AUTH_API_ERROR = "auth-api.queues.get-all.error";

    @Bean
    public Queue queueAuthApiInput() {
        return new Queue(QUEUE_AUTH_API_INPUT);
    }

    @Bean
    public Queue queueAuthApiOutput() {
        return new Queue(QUEUE_AUTH_API_OUTPUT);
    }

    @Bean
    public Queue queueAuthApiError() {
        return new Queue(QUEUE_AUTH_API_ERROR);
    }

    @Bean
    public Queue queueFavoritesInput() {
        return new Queue("favorites-api.queues.get-all.input");
    }
}
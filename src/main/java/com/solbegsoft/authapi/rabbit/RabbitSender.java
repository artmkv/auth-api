package com.solbegsoft.authapi.rabbit;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

/**
 * Rabbit service
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class RabbitSender {

    /**
     * @see RabbitTemplate
     */
    private final RabbitTemplate template;

    /**
     * @see Queue Auth API Output
     */
    private final Queue queueAuthApiOutput;

    /**
     * @see Queue Favorites Input
     */
    private final Queue queueFavoritesInput;

    /**
     * Send message to Favorites
     */
    public void sendToFavorites(String message) {
        this.template.convertAndSend(queueFavoritesInput.getName(), message);
        log.info("[ASYNC] Sent message to {} ", queueFavoritesInput.getName());
    }

    /**
     * Send message to Favorites
     */
    public void sendToAuthOutput(String message) {
        this.template.convertAndSend(queueAuthApiOutput.getName(), message);
        log.info("[ASYNC] Sent message to {} ", queueAuthApiOutput.getName());
    }
}
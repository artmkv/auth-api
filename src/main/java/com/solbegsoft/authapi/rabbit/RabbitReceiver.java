package com.solbegsoft.authapi.rabbit;


import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import static com.solbegsoft.authapi.configurations.RabbitConfig.QUEUE_AUTH_API_INPUT;
import static com.solbegsoft.authapi.configurations.RabbitConfig.QUEUE_FAVORITES_API_OUTPUT;

/**
 * Rabbit Listener
 */
@Slf4j
@RabbitListener()
@Component
public class RabbitReceiver {

    /**
     * Rabbit Handler queue Auth-Api input
     *
     * @param message string
     */
    @RabbitListener(queues = QUEUE_AUTH_API_INPUT)
    public void receiveMessageFromAuthApiInput(String message) {
        log.info("Received from {} <{}>", QUEUE_FAVORITES_API_OUTPUT, message);
    }

    /**
     * Rabbit Handler queue Auth-Api input
     *
     * @param message string
     */
    @RabbitListener(queues = QUEUE_FAVORITES_API_OUTPUT)
    public void receiveMessageFromAuthApiOutput(String message) {
        log.info("Received from {} <{}>", QUEUE_FAVORITES_API_OUTPUT, message);
    }
}
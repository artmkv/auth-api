package com.solbegsoft.authapi.rabbit;


import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Test {@link RabbitSender}
 */
@AutoConfigureMockMvc
@SpringBootTest
class RabbitSenderTest {

    /**
     * @see RabbitTemplate
     */
    @MockBean
    private RabbitTemplate rabbitTemplate;

    /**
     * @see RabbitSender
     */
    @Autowired
    private RabbitSender rabbitSender;

    /**
     * @see Queue FavoritesAPI Output
     */
    @Autowired
    private Queue queueAuthApiOutput;

    /**
     * @see Queue BeersAPI input
     */
    @Autowired
    private Queue queueFavoritesInput;

    /**
     * Test {@link RabbitSender#sendToFavorites(String)}
     */
    @Test
    void testSendToFavorites() {
        String sendString = "message";
        rabbitSender.sendToFavorites(sendString);
        verify(rabbitTemplate, times(1)).convertAndSend(queueFavoritesInput.getName(), sendString);
    }

    /**
     * Test {@link RabbitSender#sendToAuthOutput(String)}
     */
    @Test
    void testSendToAuthOutput() {
        String sendString = "message";
        rabbitSender.sendToAuthOutput(sendString);
        verify(rabbitTemplate, times(1)).convertAndSend(queueAuthApiOutput.getName(), sendString);
    }
}
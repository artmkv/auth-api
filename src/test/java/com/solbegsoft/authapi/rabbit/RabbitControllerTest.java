package com.solbegsoft.authapi.rabbit;


import com.solbegsoft.authapi.controllers.AbstractMVCTest;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Test {@link RabbitController}
 */
class RabbitControllerTest extends AbstractMVCTest {

    /**
     * General Endpoint
     */
    private static final String ENDPOINT = "/auth-api/v1/rabbit";

    /**
     * @see RabbitTemplate
     */
    @MockBean
    private RabbitTemplate rabbitTemplate;

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
     * Test {@link RabbitController#getSendMessageToRabbitBroker(String)}
     */
    @Test
    void getSendMessageToRabbitBroker_WithRequest_ShouldReturnStatusAccepted() throws Exception {
        String sendString = "messages";

        mockMvc.perform(get(ENDPOINT)
                        .param("msg", sendString))
                .andDo(print())
                .andExpect(status().isAccepted());

        verify(rabbitTemplate, times(1)).convertAndSend(queueAuthApiOutput.getName(), sendString);
        verify(rabbitTemplate, times(1)).convertAndSend(queueFavoritesInput.getName(), sendString);
    }
}
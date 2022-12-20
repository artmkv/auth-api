package com.solbegsoft.authapi.rabbit;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Rabbit Controller
 */
@Slf4j
@RestController
@RequestMapping("auth-api/v1/rabbit")
@RequiredArgsConstructor
public class RabbitController {

    /**
     * @see RabbitSender
     */
    private final RabbitSender rabbitSender;

    /**
     * Controller to send message To Rabbit Broker
     *
     * @param msg message
     * @return {@link ResponseEntity}
     */
    @GetMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<String> getSendMessageToRabbitBroker(
            @RequestParam String msg
    ) {
        log.info("#GET: Send RABBIT MESSAGE: <{}>", msg);

        rabbitSender.sendToFavorites(msg);
        rabbitSender.sendToAuthOutput(msg);

        log.info("#GET: Send SUCCESSFUL RABBIT MESSAGE: <{}>", msg);

        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
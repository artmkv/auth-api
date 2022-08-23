package com.solbegsoft.authapi.controllers;


import com.solbegsoft.authapi.exceptions.SecurityTokenException;
import com.solbegsoft.authapi.models.response.ResponseApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Application Exception Handler
 */
@Slf4j
@RestControllerAdvice
public class AuthExceptionHandler {

    /**
     * Handler {@link BadCredentialsException}
     *
     * @param e exception
     * @return {@link  ResponseApi}
     */
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseApi<String> handlerBadCredentialsException(BadCredentialsException e) {

        log.info("Exception {}", e.getMessage());
        return new ResponseApi<>(e.getMessage());
    }

    /**
     * Handler {@link SecurityTokenException}
     *
     * @param e exception
     * @return  {@link  ResponseApi}
     */
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(SecurityTokenException.class)
    public ResponseApi<String> handlerSecurityTokenException(SecurityTokenException e) {

        log.info("Exception {}", e.getMessage());
        return new ResponseApi<>(e.getMessage());
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(RuntimeException.class)
    public ResponseApi<String> handlerRuntimeException(RuntimeException e) {

        log.info("Exception {}", e.getMessage());
        return new ResponseApi<>(e.getMessage());
    }

}

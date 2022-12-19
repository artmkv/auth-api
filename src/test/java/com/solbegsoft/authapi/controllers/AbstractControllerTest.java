package com.solbegsoft.authapi.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.solbegsoft.authapi.models.requests.AuthRequest;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Abstract class for Controller Test
 */
public abstract class AbstractControllerTest extends AbstractMVCTest {

    /**
     * @see ObjectMapper
     */
    @Autowired
    protected ObjectMapper objectMapper;

    /**
     * General controllers URL
     */
    public static final String GENERAL_ENDPOINT = "/auth-api/v1/auth";

    protected String getEndPoint() {
        return GENERAL_ENDPOINT;
    }

    /**
     * Create {@link AuthRequest}
     *
     * @return {@link AuthRequest}
     */
    protected AuthRequest createAuthRequest() {
        AuthRequest request = new AuthRequest();
        request.setLogin("userR@mail.com");
        request.setPassword("87654321");

        return request;
    }

    /**
     * Create {@link AuthRequest}
     *
     * @param login    login
     * @param password password
     * @return {@link AuthRequest}
     */
    protected AuthRequest createAuthRequest(String login, String password) {
        AuthRequest request = new AuthRequest();
        request.setLogin(login);
        request.setPassword(password);
        return request;
    }
}
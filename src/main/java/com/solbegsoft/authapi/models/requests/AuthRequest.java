package com.solbegsoft.authapi.models.requests;


import lombok.Data;

/**
 * request for authentication
 */
@Data
public class AuthRequest {

    /**
     * login
     */
    private String login;

    /**
     * password
     */
    private String password;
}

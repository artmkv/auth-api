package com.solbegsoft.authapi.models.requests;


import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * request for authentication
 */
@Data
public class AuthRequest {

    /**
     * login
     */
    @NotBlank
    private String login;

    /**
     * password
     */
    @NotBlank
    private String password;
}

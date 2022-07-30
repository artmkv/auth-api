package com.solbegsoft.authapi.models.requests;


import lombok.Data;

import java.util.Set;

/**
 * Register Request model
 */
@Data
public class RegisterRequest {

    // TODO: 30.07.2022 валидация и т.п.
    private String username;

    private String email;

    private String password;

    private Set<String> roles;
}

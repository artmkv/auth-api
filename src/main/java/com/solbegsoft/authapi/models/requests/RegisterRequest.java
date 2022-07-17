package com.solbegsoft.authapi.models.requests;


import lombok.Data;

import java.util.Set;

/**
 * Register Request model
 */
@Data
public class RegisterRequest {

    private String username;

    private String email;

    private String password;

    private Set<String> roles;
}

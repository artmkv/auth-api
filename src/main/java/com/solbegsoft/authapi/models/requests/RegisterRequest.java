package com.solbegsoft.authapi.models.requests;


import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Set;

/**
 * Register Request model
 */
@Data
public class RegisterRequest {

    /**
     * Username
     */
    @NotBlank
    private String username;

    /**
     * email
     */
    @Email(message = "Email should be valid")
    @NotBlank(message = "Email cannot be empty")
    private String email;

    /**
     * password
     */
    @NotBlank(message = "Password cannot be empty")
    private String password;

    /**
     * Set users roles
     */
    private Set<String> roles;
}

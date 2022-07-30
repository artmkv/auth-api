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
    private String login; // TODO: 30.07.2022 тут должна быть валидация @NotBlank ну и с пасс тоже самое, ну и если логин является имейлом тоже можно кинуть валидацию

    /**
     * password
     */
    private String password;
}

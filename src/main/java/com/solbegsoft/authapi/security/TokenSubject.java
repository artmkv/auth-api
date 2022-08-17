package com.solbegsoft.authapi.security;


import lombok.Builder;
import lombok.Data;
import lombok.ToString;

/**
 * Token subject
 */
@Data
@ToString
@Builder
public class TokenSubject {

    /**
     * Username
     */
    String username;

    /**
     * userId
     */
    Long userId;

}

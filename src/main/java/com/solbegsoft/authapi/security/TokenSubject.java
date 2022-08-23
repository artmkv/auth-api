package com.solbegsoft.authapi.security;


import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.UUID;

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
    UUID userId;

}

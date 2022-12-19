package com.solbegsoft.authapi.security;


import lombok.*;

import java.util.UUID;

/**
 * Token subject
 */
@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TokenSubject {

    /**
     * Username
     */
   private String username;

    /**
     * userId
     */
    private UUID userId;
}

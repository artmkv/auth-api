package com.solbegsoft.authapi.security;


import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

/**
 * Class of Constant
 */
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class SecurityConstants {

    /**
     * Header Token Prefix
     */
    public static final String HEADER_TOKEN_PREFIX = "Bearer ";

    /**
     * Header authorization
     */
    public static final String HEADER_AUTHORIZATION = "Authorization";

}

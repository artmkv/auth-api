package com.solbegsoft.authapi.security;


/**
 * token service
 */
public interface TokenService {

    String createToken(String username);

    String validateTokenAndGetUsername(String token);
}

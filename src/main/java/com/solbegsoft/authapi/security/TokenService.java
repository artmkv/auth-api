package com.solbegsoft.authapi.security;


/**
 * Token service
 */
public interface TokenService {

    /**
     * Create Token
     *
     * @param username
     * @return
     */
    String createToken(String username);

    /**
     * validate Token
     *
     * @param token
     * @return username
     */
    String validateTokenAndGetUsername(String token);

    /**
     * create Bearer + token
     *
     * @param token
     * @return string
     */
    String createBearer(String token);
}

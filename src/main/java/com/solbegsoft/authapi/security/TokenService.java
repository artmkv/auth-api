package com.solbegsoft.authapi.security;


/**
 * Token service
 */
public interface TokenService {

    /**
     * Create Token
     *
     * @param username  Username
     * @param id User ID
     * @return token
     */
    String createTokenUsingTokenSubject(String username, Long id);

    /**
     * validate Token
     *
     * @param token Token
     * @return username
     */
    TokenSubject validateTokenAndGetTokenSubject(String token);

    /**
     * create Bearer + token
     *
     * @param token token
     * @return string
     */
    String createBearer(String token);
}

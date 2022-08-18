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
     * Validate Token
     *
     * @param token Token
     * @return username
     */
    TokenSubject validateTokenAndGetTokenSubject(String token);

    /**
     * Create Bearer + token
     *
     * @param token token
     * @return {@link TokenSubject}
     */
    String createBearer(String token);
}

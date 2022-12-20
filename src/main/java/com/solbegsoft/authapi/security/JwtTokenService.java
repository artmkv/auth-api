package com.solbegsoft.authapi.security;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.solbegsoft.authapi.exceptions.SecurityTokenException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.xml.bind.DatatypeConverter;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.UUID;

import static com.solbegsoft.authapi.security.SecurityConstants.HEADER_TOKEN_PREFIX;

/**
 * jwt token service
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class JwtTokenService implements TokenService {

    /**
     * Object Mapper
     */
    private final ObjectMapper objectMapper;

    /**
     * token secret key
     */
    @Value("${JWT_SECRET:Fx54Yey23Ur}")
    private String key;

    /**
     * token expiration
     */
    @Value("${JWT_EXPIRATION_S:300}")
    private int expirationS;

    public String createTokenUsingTokenSubject(String username, UUID userId) {
        TokenSubject sub = new TokenSubject(username, userId);
        String result;
        try {
            result = objectMapper.writeValueAsString(sub);
        } catch (JsonProcessingException e) {
            throw new SecurityTokenException(e.getMessage());
        }

        return Jwts.builder()
                .setSubject(result)
                .setIssuedAt(new Date())
                .setExpiration(Date.from(LocalDateTime.now().plusSeconds(expirationS).toInstant(ZoneOffset.UTC)))
                .signWith(SignatureAlgorithm.HS256, key)
                .compact();
    }

    @Override
    public TokenSubject validateTokenAndGetTokenSubject(String token) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(key))
                    .parseClaimsJws(token).getBody();
            Date expiration = claims.getExpiration();
            if (expiration.before(Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC)))) {
                log.warn("BAD_CREDENTIAL: {}", claims.getSubject());
            }
            return objectMapper.readValue(claims.getSubject(), TokenSubject.class);
        } catch (JsonProcessingException | JwtException e) {
            throw new SecurityTokenException(e.getMessage());
        }
    }

    @Override
    public String createBearer(String token) {
        return HEADER_TOKEN_PREFIX + token;
    }
}
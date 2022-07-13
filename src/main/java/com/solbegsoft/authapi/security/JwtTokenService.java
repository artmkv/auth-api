package com.solbegsoft.authapi.security;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.xml.bind.DatatypeConverter;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

/**
 * jwt token service
 */
@Service
@RequiredArgsConstructor
public class JwtTokenService implements TokenService {

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

    @Override
    public String createToken(String username) {

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(Date.from(LocalDateTime.now().plusSeconds(expirationS).toInstant(ZoneOffset.UTC)))
                .signWith(SignatureAlgorithm.HS256, key)
                .compact();
    }

    @Override
    public String validateTokenAndGetUsername(String token) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(key))
                    .parseClaimsJws(token).getBody();
            Date expiration = claims.getExpiration();
            if (expiration.before(Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC)))) {
                return null;
            }
            return claims.getSubject();
        } catch (Exception e) {
            return null;
        }
    }
}

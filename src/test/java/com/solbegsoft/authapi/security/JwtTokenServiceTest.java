package com.solbegsoft.authapi.security;


import com.solbegsoft.authapi.models.entities.ERole;
import com.solbegsoft.authapi.models.entities.Role;
import com.solbegsoft.authapi.models.entities.User;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Jwt Token Service Test
 */
@SpringBootTest
class JwtTokenServiceTest {

    public static final String BEARER_TOKEN = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ7XCJ1c2VybmFtZVwiOlwidXNlclJAbWFpbC5jb21cIixcInVzZXJJZFwiOlwiMTc3N2FmZmUtNTVmYi0xMWVkLWIwZjQtNWY5ZGIzYjAyYjBkXCJ9IiwiaWF0IjoxNjY2OTU1MzA4LCJleHAiOjE2NjY5NjI4MDh9.-zsEHnQWLO2BV6nvtzODLn9UIeOf8mTHdhSNbqunvO0";

    /**
     * @see JwtTokenService
     */
    @Autowired
    private JwtTokenService jwtTokenService;

    /**
     * @see PasswordEncoder
     */
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Test with any strings method {@link JwtTokenService#createBearer(String)}
     *
     * @param token string token
     */
    @ParameterizedTest
    @ValueSource(strings = {"132456", "  _--   ", "__)(#MFSL@324", "Bearer egggdg", "Bearer !!----"})
    @EmptySource
    @NullSource
    void validateTokenAndGetTokenSubject_ShouldReturn(String token) {

        UUID userId = UUID.fromString("1777affe-55fb-11ed-b0f4-5f9db3b02b0d");
        User user = createUser("userR", "userR@mail.com", "87654321");
        user.setId(userId);
        String encodePassword = passwordEncoder.encode(user.getPassword());

        String authenticateToken = BEARER_TOKEN;

        TokenSubject tokenSubject = TokenSubject.builder()
                .userId(user.getId())
                .username(user.getUsername())
                .build();
    }

    /**
     * Test with any strings method {@link JwtTokenService#createBearer(String)}
     *
     * @param token string token
     */
    @ParameterizedTest
    @ValueSource(strings = {"132456", "  _--   ", "__)(#MFSL@324", "@!fsf6!3_##@@!DFSNFI"})
    @EmptySource
    @NullSource
    void createBearer(String token) {
        String expected = "Bearer " + token;
        String actual = jwtTokenService.createBearer(token);

        assertEquals(expected, actual);
    }

    /**
     * Create user
     *
     * @param username username
     * @param email    email
     * @param password password
     * @return {@link User}
     */
    private User createUser(String username, String email, String password) {
        Role role = new Role();
        role.setName(ERole.ROLE_READER.name());
        Set<Role> setRoles = Set.of(role);

        User result = new User();
        result.setId(UUID.randomUUID());
        result.setUsername(username);
        result.setEmail(email);
        result.setPassword(password);
        result.setRoles(setRoles);
        result.setGender("Male");
        result.setBirthday(LocalDate.of(1658, 11, 5));
        result.setCountry("Mars");

        return result;
    }
}
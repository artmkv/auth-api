package com.solbegsoft.authapi.controllers;


import com.solbegsoft.authapi.models.dtos.UserDetailsDto;
import com.solbegsoft.authapi.models.entities.ERole;
import com.solbegsoft.authapi.models.entities.Role;
import com.solbegsoft.authapi.models.entities.User;
import com.solbegsoft.authapi.models.requests.AuthRequest;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Set;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Test for {@link AuthenticationController}
 */
class AuthenticationControllerTest extends AbstractControllerTest {

    /**
     * @see WebApplicationContext
     */
    @Autowired
    private WebApplicationContext context;

    /**
     * Setup context before start test
     */
    @Before
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
    }

    /**
     * Test method {@link AuthenticationController#getAuthentication(AuthRequest)}
     *
     * @throws Exception exception
     */
    @Test
    void testGetAuthentication_WithoutAuthentication_ShouldReturnForbidden() throws Exception {
        AuthRequest authRequest = createAuthRequest();
        String login = "userR@mail.com";
        String password = "87654321";

        UserDetailsDto dto = new UserDetailsDto();
        dto.setEmail(login);
        dto.setPassword(password);

        mockMvc.perform(post(getEndPoint()))
                .andDo(print());
    }

    /**
     * Test method {@link AuthenticationController#getAuthentication(AuthRequest)}
     *
     * @throws Exception exception
     */
    @Test
    void testGetAuthentication_WithAuthentication_ShouldReturnForbidden() throws Exception {

        var authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_READER"));
        var authenticationToken = new UsernamePasswordAuthenticationToken(null, null, authorities);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        mockMvc.perform(get("/auth-api/v1/test/r"))
                .andDo(print())
                .andExpect(status().isForbidden());
    }

    /**
     * Test method {@link AuthenticationController#getAuthentication(AuthRequest)}
     *
     * @throws Exception exception
     */
    @Test
    void testGetAuthentication_WithCorrectRequest_ShouldReturnUserNotFoundException() throws Exception {

        User user = createUser("userR", "userR@mail.com", "87654321");
        AuthRequest request = createAuthRequest(user.getEmail(), user.getPassword());

        mockMvc.perform(post(getEndPoint())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
                ).andDo(print())
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.data").value("Bad credentials"));
    }

    /**
     * Create user
     *
     * @param username username
     * @param email    e-mail
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
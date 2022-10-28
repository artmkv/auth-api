package com.solbegsoft.authapi.controllers;


import com.solbegsoft.authapi.models.entities.ERole;
import com.solbegsoft.authapi.models.entities.Role;
import com.solbegsoft.authapi.models.entities.User;
import com.solbegsoft.authapi.models.requests.AuthRequest;
import com.solbegsoft.authapi.repositories.RoleRepository;
import com.solbegsoft.authapi.repositories.UserRepository;
import com.solbegsoft.authapi.services.UserService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Test for {@link AuthenticationController}
 */
class AuthenticationControllerTest extends AbstractControllerTest {

    /**
     * @see RoleRepository
     */
    @MockBean
    private RoleRepository roleRepository;

    /**
     * @see UserRepository
     */
    @MockBean
    private UserRepository userRepository;

    @MockBean
    private UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken;

    @MockBean
    private UserService userService;

    @Test
    void testTestTest() throws Exception{

    }

    @Test
    void testGetAuthentication_WithCorrectRequest_ShouldReturnToken() throws Exception {
        AuthRequest request = createAuthRequest();

        usernamePasswordAuthenticationToken.setAuthenticated(true);
        when(userRepository.findByEmail(request.getLogin())).thenReturn(Optional.empty());

        when(usernamePasswordAuthenticationToken.isAuthenticated()).thenReturn(true);

        mockMvc.perform(post(getEndPoint())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
                ).andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @WithUserDetails("user@test.io")
    void testGetAuthentication_WithCorrectRequest_ShouldReturnOk() throws Exception {
        AuthRequest request = createAuthRequest("user@test.io", "12345678");

        mockMvc.perform(post(getEndPoint())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
                ).andDo(print())
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.data").value(Matchers.stringContainsInOrder("Bearer")));
    }


    @Test
    @WithMockUser(username = "userR@mail.com", password = "87654321", roles = {"ROLE_READER"})
    void testGetAuthentication_WithCorrectRequest22_ShouldReturnSomething() throws Exception {

        User user = createUser("test@mail.com", "87654321");

        AuthRequest request = createAuthRequest("userR@mail.com", "87654321");

        Optional<User> optionalUser = Optional.of(user);
        when(userRepository.findByEmail(request.getLogin())).thenReturn(optionalUser);

        mockMvc.perform(post(getEndPoint())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
                ).andDo(print())
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.data").value(Matchers.stringContainsInOrder("Bearer")));
    }

    @Test
    void testGetAuthentication_WithCorrectRequest_ShouldReturnUserNotFoundException() throws Exception {

        User user = createUser("userR@mail.com", "87654321");
        AuthRequest request = createAuthRequest(user.getEmail(), user.getPassword());

        mockMvc.perform(post(getEndPoint())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
                ).andDo(print())
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.data").value("Bad credentials"));
    }

    private User createUser(String email, String password) {
        Role role = new Role();
        role.setName(ERole.ROLE_READER.name());
        Set<Role> setRoles = Set.of(role);

        User result = new User();
        result.setId(UUID.randomUUID());
        result.setUsername("NameUser");
        result.setEmail(email);
        result.setPassword(password);
        result.setRoles(setRoles);
        result.setGender("Male");
        result.setBirthday(LocalDate.of(1658, 11, 5));
        result.setCountry("Mars");

        return result;
    }

}
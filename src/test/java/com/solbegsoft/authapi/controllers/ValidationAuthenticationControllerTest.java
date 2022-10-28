package com.solbegsoft.authapi.controllers;


import com.solbegsoft.authapi.models.requests.AuthRequest;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Test validation request for Authentication Controller
 */
class ValidationAuthenticationControllerTest extends AbstractControllerTest {

    @MockBean
    private UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken;

    /**
     * Test with any data in AuthRequest {@link AuthenticationController#getAuthentication(AuthRequest)}
     *
     * @throws Exception Exception
     */
    @ParameterizedTest
    @ValueSource(strings = {"help@test.io", "123456", "____", "te@st!"})
    @EmptySource
    @NullSource
    void testGetAuthentication_WithUncorrectedDataInRequest_ShouldReturnForbiddenAndMessage(String parameter) throws Exception {
        AuthRequest request = createAuthRequest(parameter, parameter);

        mockMvc.perform(post(getEndPoint())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
                ).andDo(print())
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.data").value("Bad credentials"));
    }

    /**
     * Test {@link AuthenticationController#getAuthentication(AuthRequest)}
     *
     * @throws Exception Exception
     */
    @Test
    void testGetAuthentication_WithNullRequest_ShouldReturnBadRequestAndMessage() throws Exception {
        AuthRequest request = null;

        mockMvc.perform(post(getEndPoint())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
                ).andDo(print())
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.data").value(Matchers.stringContainsInOrder("Required request body is missing")));
    }
}
package com.solbegsoft.authapi.controllers;


import com.solbegsoft.authapi.models.requests.AuthRequest;
import com.solbegsoft.authapi.models.response.ResponseApi;
import com.solbegsoft.authapi.security.JwtTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Authentication controller
 */
@RestController
@RequestMapping("auth-api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    /**
     * @see UserDetails
     */
    private final UserDetailsService userDetailsService;

    /**
     * @see AuthenticationManager
     */
    private final AuthenticationManager authenticationManager;

    /**
     * @see JwtTokenService
     */
    private final JwtTokenService jwtTokenService;

    /**
     * Get token
     *
     * @return {@link ResponseApi} with token
     */
    @PostMapping
    public ResponseApi<String> getAuthentication(@RequestBody AuthRequest request) throws Exception {

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    request.getLogin(),
                    request.getPassword()));
        } catch (BadCredentialsException e) {
            throw new Exception("Invalid username or password");
        }
        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getLogin());
        final String token = jwtTokenService.createToken(userDetails.getUsername());

        return new ResponseApi<>(jwtTokenService.createBearer(token));
    }
}
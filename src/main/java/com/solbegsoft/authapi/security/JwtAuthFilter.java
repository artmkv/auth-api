package com.solbegsoft.authapi.security;


import com.solbegsoft.authapi.services.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * JWT Authorization Filter
 */
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    /**
     * Header token prefix
     */
    private static final String HEADER_TOKEN_PREFIX = "Bearer ";
    /**
     * Header authorization
     */
    private static final String HEADER_AUTHORIZATION = "Authorization";

    /**
     * @see UserDetailsServiceImpl
     */
    private final UserDetailsServiceImpl userDetailsService;

    /**
     * @see JwtTokenService
     */
    private final JwtTokenService jwtTokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authorizationHeader = request.getHeader(HEADER_AUTHORIZATION);
        String token = null;

        if (authorizationHeader != null && authorizationHeader.startsWith(HEADER_TOKEN_PREFIX)) {
            token = authorizationHeader.replace(HEADER_TOKEN_PREFIX, "");
            String username = jwtTokenService.validateTokenAndGetUsername(token);
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            if (Objects.nonNull(username)) {
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                token = jwtTokenService.createToken(username);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        response.setHeader(HEADER_AUTHORIZATION, token);
        filterChain.doFilter(request, response);
    }
}

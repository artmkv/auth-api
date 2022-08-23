package com.solbegsoft.authapi.security;


import com.netflix.zuul.context.RequestContext;
import com.solbegsoft.authapi.services.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

import static com.solbegsoft.authapi.security.SecurityConstants.HEADER_AUTHORIZATION;
import static com.solbegsoft.authapi.security.SecurityConstants.HEADER_TOKEN_PREFIX;

/**
 * JWT Authorization Filter
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    /**
     * @see UserDetailsServiceImpl
     */
    private final UserDetailsServiceImpl userDetailsService;

    /**
     * @see TokenService
     */
    private final TokenService jwtTokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token = request.getHeader(HEADER_AUTHORIZATION);
        String username;
        UUID userId = null;
        if (Objects.nonNull(token) && token.startsWith(HEADER_TOKEN_PREFIX)) {
            TokenSubject tokenSubject = jwtTokenService
                    .validateTokenAndGetTokenSubject(token.replace(HEADER_TOKEN_PREFIX, ""));
            username = tokenSubject.getUsername();
            userId = tokenSubject.getUserId();

            if (Objects.nonNull(username)) {
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                var authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } else {
            SecurityContextHolder.getContext().setAuthentication(null);
        }
        RequestContext ctx = RequestContext.getCurrentContext();
        ctx.addZuulRequestHeader("userId", String.valueOf(userId));
        response.setHeader(HEADER_AUTHORIZATION, token);
        filterChain.doFilter(request, response);
    }
}
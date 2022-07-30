package com.solbegsoft.authapi.security;


import com.solbegsoft.authapi.services.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
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

/**
 * JWT Authorization Filter
 */
@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    /**
     * Header token prefix
     */
    private static final String HEADER_TOKEN_PREFIX = "Bearer "; // TODO: 30.07.2022 нарушен принцип DRY у тебея 2 константы, вторая такая же в JwtTokenService

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
    // TODO: 30.07.2022 используешь реализацию вместо абстракции не гуд
    private final JwtTokenService jwtTokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token = request.getHeader(HEADER_AUTHORIZATION);;

        if (token != null && token.startsWith(HEADER_TOKEN_PREFIX)) { // TODO: 30.07.2022 я бы юзал Objects.nonNull(token)
            token = token.replace(HEADER_TOKEN_PREFIX, "");
            String username = jwtTokenService.validateTokenAndGetUsername(token);

            if (Objects.nonNull(username)) {
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                var authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                token = jwtTokenService.createToken(username);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        else {
            SecurityContextHolder.getContext().setAuthentication(null);
        }
        // TODO: 30.07.2022 воу воу, нашел бажину 49 строка у тебя токен и вдруг потом мы идем по ветке else и ты тут к Bearer18230123 фигачишь еще раз Bearer
        response.setHeader(HEADER_AUTHORIZATION, jwtTokenService.createBearer(token));
        filterChain.doFilter(request, response);
    }
}

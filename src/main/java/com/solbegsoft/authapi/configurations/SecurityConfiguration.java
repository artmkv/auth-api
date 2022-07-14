package com.solbegsoft.authapi.configurations;


import com.solbegsoft.authapi.security.AuthEntryPointJwt;
import com.solbegsoft.authapi.security.JwtAuthFilter;
import com.solbegsoft.authapi.security.JwtTokenService;
import com.solbegsoft.authapi.services.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Collections;

/**
 * Configuration
 */
@Configuration
@RequiredArgsConstructor
public class SecurityConfiguration {

    /**
     * @see UserDetailsServiceImpl
     */
    private final UserDetailsServiceImpl userDetailsService;

    /**
     * @see JwtTokenService
     */
    private final JwtTokenService jwtTokenService;

    /**
     * @see AuthEntryPointJwt
     */
    private final AuthEntryPointJwt authEntryPointJwt;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return authentication -> {
            if (authentication.isAuthenticated()) {
                return new UsernamePasswordAuthenticationToken(
                        authentication.getPrincipal(),
                        authentication.getCredentials(),
                        Collections.singletonList(new SimpleGrantedAuthority("READER"))
                );
            }
            throw new UsernameNotFoundException(authentication.getPrincipal().toString());
        };
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf().disable();
        http.cors().disable();
        http.exceptionHandling().authenticationEntryPoint(authEntryPointJwt);
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeRequests().antMatchers(HttpMethod.POST, "auth-api/v1/auth").permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.POST, "auth-api/v1/test/signup").permitAll();
        http.authorizeRequests().antMatchers("auth-api/v1/test/gen").permitAll();

        http.authorizeRequests().anyRequest().permitAll();

        JwtAuthFilter filter = new JwtAuthFilter(userDetailsService, jwtTokenService);
        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {

        return web -> {
            web.ignoring().antMatchers(HttpMethod.POST, "auth-api/v1/auth/signup");
        };
    }

}
package com.solbegsoft.authapi.configurations;


import com.solbegsoft.authapi.security.JwtAuthFilter;
import com.solbegsoft.authapi.security.JwtTokenService;
import com.solbegsoft.authapi.services.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Security configuration
 */@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        securedEnabled = true,
        jsr250Enabled = true,
        prePostEnabled = true
)
@RequiredArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {


    /**
     * {@link UserDetailsService}
     */
    private final UserDetailsServiceImpl userDetailService;
    /**
     * {@link JwtTokenService}
     */
    private final JwtTokenService jwtTokenService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService).passwordEncoder(passwordEncoder());
    }

    /**
     * Create bean
     *
     * @return {@link AuthenticationManager}
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * Create bean
     *
     * @return {@link PasswordEncoder}
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable();
        http.cors().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http
                .authorizeRequests().antMatchers(HttpMethod.POST, "/auth-api/v1/auth").permitAll()
                .antMatchers("/auth-api/v1/test/all").permitAll()
                .antMatchers(HttpMethod.POST,"/auth-api/v1/test/s").permitAll()
                .antMatchers("/auth-api/v1/test/r").hasAuthority("ROLE_READER")
                .antMatchers("/auth-api/v1/test/w").hasAuthority("ROLE_WRITER");
        http.authorizeRequests().anyRequest().authenticated();

        JwtAuthFilter tokenFilter = new JwtAuthFilter(userDetailService, jwtTokenService);
        http.addFilterBefore(tokenFilter, UsernamePasswordAuthenticationFilter.class);

    }

    @Override
    public void configure(WebSecurity web) {

        web.ignoring().antMatchers(HttpMethod.POST, "/auth-api/v1/auth");
        web.ignoring().antMatchers(HttpMethod.OPTIONS, "/auth-api/v1/**");
    }
}
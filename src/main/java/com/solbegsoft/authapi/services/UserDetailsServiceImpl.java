package com.solbegsoft.authapi.services;


import com.solbegsoft.authapi.models.mappers.UserMapper;
import com.solbegsoft.authapi.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * CustomUserDetailsService
 */
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    /**
     * @see UserRepository
     */
    private final UserRepository userRepository;

    /**
     * @see UserMapper
     */
    private final UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return Optional.ofNullable(userRepository.findByUsername(username))
                .map(usr -> userMapper.toDto(usr.get()))
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }
}
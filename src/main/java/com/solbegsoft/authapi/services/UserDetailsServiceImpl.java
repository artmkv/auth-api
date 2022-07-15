package com.solbegsoft.authapi.services;


import com.solbegsoft.authapi.models.mappers.UserMapper;
import com.solbegsoft.authapi.repositories.RoleRepository;
import com.solbegsoft.authapi.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
     * @see RoleRepository
     */
    private final RoleRepository roleRepository;

    /**
     * @see UserMapper
     */
    private final UserMapper userMapper;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return Optional.ofNullable(userRepository.findByUsername(username))
                .map(usr -> userMapper.toDto(usr.get()))
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }
}
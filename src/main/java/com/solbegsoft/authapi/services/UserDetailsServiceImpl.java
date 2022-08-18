package com.solbegsoft.authapi.services;


import com.solbegsoft.authapi.models.dtos.UserDetailsDto;
import com.solbegsoft.authapi.models.mappers.UserConverter;
import com.solbegsoft.authapi.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * UserDetailsService implement
 */
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    /**
     * @see UserRepository
     */
    private final UserRepository userRepository;

    /**
     * @see UserConverter
     */
    private final UserConverter userConverter;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return userRepository.findByEmail(username)
                .map(userConverter::convertToDto)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }

}
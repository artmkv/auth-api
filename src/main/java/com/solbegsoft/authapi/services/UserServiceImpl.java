package com.solbegsoft.authapi.services;


import com.solbegsoft.authapi.models.entities.ERole;
import com.solbegsoft.authapi.models.entities.Role;
import com.solbegsoft.authapi.models.entities.User;
import com.solbegsoft.authapi.models.requests.RegisterRequest;
import com.solbegsoft.authapi.repositories.RoleRepository;
import com.solbegsoft.authapi.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * User Service
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    /**
     * @see UserRepository
     */
    private final UserRepository userRepository;
    /**
     * @see RoleRepository
     */
    private final RoleRepository roleRepository;

    /**
     * @see PasswordEncoder
     */
    private final PasswordEncoder passwordEncoder;

    @Override
    public User registryUser(RegisterRequest request) {
        Set<String> reqRoles = request.getRoles();
        Set<Role> roles = new HashSet<>();
        if (reqRoles == null) {
            Role readerRole = roleRepository.findById(ERole.ROLE_READER.name())
                    .orElseThrow(() -> new RuntimeException("Error, Role READER is not found"));
            roles.add(readerRole);
        }

        User userToSave = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .roles(roles)
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        return userRepository.save(userToSave);
    }
}

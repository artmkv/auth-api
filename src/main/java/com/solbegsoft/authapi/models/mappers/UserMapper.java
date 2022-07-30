package com.solbegsoft.authapi.models.mappers;


import com.solbegsoft.authapi.models.dtos.UserDetailsDto;
import com.solbegsoft.authapi.models.entities.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * UserDetailsDto mapper
 */
@Component
public class UserMapper {

    /**
     * Mapped User to UserDetails
     *
     * @param user
     * @return
     */
    public UserDetails toDto(User user) {

        return Optional.ofNullable(user)
                .map(x -> new UserDetailsDto( // TODO: 30.07.2022 что за 'X' ?????  ну и уже тогда добавляй mapstruct!!
                        x.getUsername(),
                        x.getEmail(),
                        x.getPassword(),
                        x.getRoles()))
                .orElse(null);
    }
}
package com.solbegsoft.authapi.models.mappers;


import com.solbegsoft.authapi.models.dtos.UserDetailsDto;
import com.solbegsoft.authapi.models.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * UserDetailsDto mapper
 */
@Component
@RequiredArgsConstructor
public class UserMapper {

    private final UserDetailsDto userDetailsDto;
    public UserDetails toDto(User user){

        if(Objects.nonNull(user)){
            return new UserDetailsDto(
                    user.getEmail(),
                    user.getPassword(),
                    user.getRoles());
        }
        return null;
    }
}
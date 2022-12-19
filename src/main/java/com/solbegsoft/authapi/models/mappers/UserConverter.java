package com.solbegsoft.authapi.models.mappers;


import com.solbegsoft.authapi.models.dtos.UserDetailsDto;
import com.solbegsoft.authapi.models.entities.User;
import org.mapstruct.Mapper;

/**
 * User converter
 */
@Mapper(componentModel = "spring")
public interface UserConverter {

    /**
     * Convert {@link User} and {@link UserDetailsDto}
     *
     * @param user {@link User}
     * @return {@link UserDetailsDto}
     */
   UserDetailsDto convertToDto(User user);
}

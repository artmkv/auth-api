package com.solbegsoft.authapi.services;


import com.solbegsoft.authapi.models.entities.User;
import com.solbegsoft.authapi.models.requests.RegisterRequest;

/**
 * User Service Interface
 */
public interface UserService{

   User registryUser(RegisterRequest user);

}

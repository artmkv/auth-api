package com.solbegsoft.authapi.repositories;


import com.solbegsoft.authapi.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Interface User Repository
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
    // TODO: 30.07.2022 вроде же за логин решили юзать имейл? если так, то зачем мы ищем по юзернейму?

    Boolean existsByUsername(String username);

    Optional<User> findByEmail(String email);

    Boolean existsByEmail(String email);

}

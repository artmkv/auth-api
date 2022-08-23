package com.solbegsoft.authapi.repositories;


import com.solbegsoft.authapi.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * Interface User Repository
 */
@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    /**
     * Find by email
     *
     * @param email
     * @return
     */
    Optional<User> findByEmail(String email);

}

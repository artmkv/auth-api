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
    /**
     * Find by email
     *
     * @param email
     * @return
     */
    Optional<User> findByEmail(String email);

}

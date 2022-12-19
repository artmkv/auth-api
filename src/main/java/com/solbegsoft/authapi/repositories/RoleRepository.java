package com.solbegsoft.authapi.repositories;


import com.solbegsoft.authapi.models.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Interface Role Repository
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, String> {

    @Override
    Optional<Role> findById(String s);
}
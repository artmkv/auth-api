package com.solbegsoft.authapi.repositories;


import com.solbegsoft.authapi.models.entities.ERole;
import com.solbegsoft.authapi.models.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * Interface Role Repository
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, UUID> {

    Optional<Role> findByName(ERole name);
}
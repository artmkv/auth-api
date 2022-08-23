package com.solbegsoft.authapi.models.entities;


import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

/**
 * Role
 */
@Entity
@Getter
@Setter
@Builder
@Table(name = "roles", schema = "auth_service")
@AllArgsConstructor
@NoArgsConstructor
public class Role {

    /**
     * @see ERole
     */
    @Id
    @Column(length = 50, unique = true)
    private String name;

    /**
     *  Collections users and roles
     */
    @ManyToMany(mappedBy = "roles")
    private List<User> users;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return Objects.equals(name, role.name) && Objects.equals(users, role.users);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, users);
    }
}
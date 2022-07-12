package com.solbegsoft.authapi.models.entities;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

/**
 * Role
 */
@Entity
@Getter
@Setter
@ToString
@Table(name = "roles")
@NoArgsConstructor
public class Role {

    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * @see ERole
     */
    @Enumerated(EnumType.STRING)
    @Column(length = 50)
    private ERole name;

    /**
     *  Collections users and roles
     */
    @ToString.Exclude
    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Role role = (Role) o;
        return id != null && Objects.equals(id, role.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
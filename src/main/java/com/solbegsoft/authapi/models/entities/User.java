package com.solbegsoft.authapi.models.entities;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;
import java.util.UUID;

/**
 * User entity
 */
@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "users")
public class User {

    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "uuid")
    private UUID id;

    /**
     * username
     */
    @Column(name = "username", unique = true, nullable = false)
    private String username;

    /**
     * password encrypted
     */
    @Column(name = "password", nullable = false)
    private String password;

    /**
     * email
     */
    @Column(name = "email", unique = true, nullable = false)
    private String email;

    /**
     * role
     */
    @Column(name = "role_id")
    private String role;

    /**
     * Equals
     *
     * @param o object
     * @return true/false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User user = (User) o;
        return id != null && Objects.equals(id, user.id);
    }

    /**
     * hashcode
     *
     * @return integer
     */
    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
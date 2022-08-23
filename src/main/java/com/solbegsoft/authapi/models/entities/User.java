package com.solbegsoft.authapi.models.entities;


import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

/**
 * User entity
 */
@Entity
@Getter
@Setter
@Builder
@Table(name = "users", schema = "auth_service")
@AllArgsConstructor
@NoArgsConstructor
public class User {

    /**
     * id
     */
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", columnDefinition = "uuid")
    private UUID id;

    /**
     * username
     */
    @Column(name = "username", nullable = false)
    private String username;

    /**
     * email
     */
    @Column(name = "email", unique = true, nullable = false)
    private String email;

    /**
     * password encrypted
     */
    @Column(name = "password", nullable = false)
    private String password;

    /**
     * Country
     */
    @Column(name = "country")
    private String country;

    /**
     * Gender
     */
    @Column(name = "gender")
    private String gender;

    /**
     * Date of birth
     */
    @Column(name = "birthday")
    private LocalDate birthday;

    /**
     * Collections users and roles
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles", schema = "auth_service",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_name", referencedColumnName = "name"))
    private Set<Role> roles;

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
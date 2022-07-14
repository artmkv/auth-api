package com.solbegsoft.authapi.models.entities;


import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

/**
 * User entity
 */
@Entity
@Data
@Builder
@Table(name = "users", schema = "auth_service")
@NoArgsConstructor
@AllArgsConstructor
public class User {

    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * username
     */
    @Column(name = "username", unique = true, nullable = false)
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
    @Column(name = "country", nullable = false)
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
    @ToString.Exclude
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
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
package com.solbegsoft.authapi.models.entities;


import lombok.*;
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
@Builder
@Table(name = "roles", schema = "auth_service")
@AllArgsConstructor
@NoArgsConstructor
public class Role {

    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // TODO: 30.07.2022 ну фе-фе-фе юзай UUID )) а вообще зачем нам тут id? у нас же роль сама по себе уникальна, можешь ее использовать в виде идентификатора

    /**
     * @see ERole
     */
    @Column(length = 50)
    private String name;

    // TODO: 30.07.2022 зачем нам связь с юзерами? более того ты не используешь этот филд в проекте, зачем он? нарушен принцип YAGNI!
    /**
     *  Collections users and roles
     */
    @ManyToMany(mappedBy = "roles", fetch = FetchType.EAGER)
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
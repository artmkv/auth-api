package com.solbegsoft.authapi.models.dtos;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.solbegsoft.authapi.models.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * User detail dto
 */
@Data
@AllArgsConstructor
public class UserDetailsDto implements UserDetails {

    /**
     * email
     */
    private String email;

    /**
     * password
     */
    @JsonIgnore
    private String password;

    /**
     * roles
     */
    private Set<Role> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName().name()))
                .collect(Collectors.toList());
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
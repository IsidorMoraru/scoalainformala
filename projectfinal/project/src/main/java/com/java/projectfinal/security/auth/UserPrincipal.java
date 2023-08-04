package com.java.projectfinal.security.auth;

import com.java.projectfinal.model.entity.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Data
@Builder
public class UserPrincipal implements UserDetails {
    private UUID id;
    private String email;
    private String password;
    private boolean enabled;
    private UserRole role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.toString()));
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
        return !"EXPIRED".equals(enabled);
    }

    @Override
    public boolean isAccountNonLocked() {
        return !"LOCKED".equals(enabled);
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !"EXPIRED".equals(enabled);
    }

    @Override
    public boolean isEnabled() {
        return !"INACTIVE".equals(enabled);
    }
}
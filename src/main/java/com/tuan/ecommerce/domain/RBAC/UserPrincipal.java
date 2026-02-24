package com.tuan.ecommerce.domain.RBAC;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserPrincipal implements UserDetails {
    private User user;

    public UserPrincipal(User user) {
        this.user = user;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Set<GrantedAuthority> authorities = new HashSet<>();

        for (UserRole userRole : user.getUserRoles()) {

            // ROLE_
            authorities.add(
                new SimpleGrantedAuthority("ROLE_" + userRole.getRole().getName())
            );

            // PERMISSIONS
            for (RolePermission rp : userRole.getRole().getRolePermission()) {
                authorities.add(
                    new SimpleGrantedAuthority(rp.getPermission().getName())
                );
            }
        }

        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

     @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return user.getStatus() != com.tuan.ecommerce.domain.enumeration.Status.INACTIVE;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return user.getStatus() == com.tuan.ecommerce.domain.enumeration.Status.ACTIVE;
    }
}

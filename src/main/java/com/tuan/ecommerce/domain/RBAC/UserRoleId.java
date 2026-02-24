package com.tuan.ecommerce.domain.RBAC;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class UserRoleId implements Serializable{
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "role_id")
    private Long roleId;

    public UserRoleId() {

    }

    public UserRoleId(Long userId, Long roleId) {
        this.userId = userId;
        this.roleId = roleId;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        UserRoleId userRolesId = (UserRoleId) o;
        return Objects.equals(userId, userRolesId.userId) &&
               Objects.equals(roleId, userRolesId.roleId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleId, userId);
    }
}

package com.tuan.ecommerce.domain.RBAC;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class UserRoleId implements Serializable{
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "roles_id")
    private Long rolesId;

    public UserRoleId() {

    }

    

    public UserRoleId(Long userId, Long rolesId) {
        this.userId = userId;
        this.rolesId = rolesId;
    }



    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        UserRoleId userRolesId = (UserRoleId) o;
        return Objects.equals(userId, userRolesId.userId) &&
               Objects.equals(rolesId, userRolesId.rolesId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rolesId, userId);
    }
}

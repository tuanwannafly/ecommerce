package com.tuan.ecommerce.domain.RBAC;

import java.util.Objects;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "user_role",
    uniqueConstraints= {
        @UniqueConstraint(columnNames={"user_id", "role_id"})
    }
)
public class UserRole {
    @EmbeddedId
    private UserRoleId userRoleId;

    @ManyToOne
    @MapsId("roleId")
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    protected  UserRole() {

    }

    public UserRole(Role role, User user) {
        this.role = role;
        this.user = user;
        this.userRoleId = new UserRoleId(
                user.getId(),
                role.getId()
        );
    }

    public Role getRole() {
        return role;
    }

    public User getUser() {
        return user;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        UserRole that = (UserRole) o;
        return Objects.equals(userRoleId, that.userRoleId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userRoleId);
    }

}

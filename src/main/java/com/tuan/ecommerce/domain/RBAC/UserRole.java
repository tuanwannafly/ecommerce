package com.tuan.ecommerce.domain.RBAC;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("rolesId")
    @JoinColumn(name = "roles_id", nullable = false)
    private Role roles;


    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    protected  UserRole() {

    }

    public UserRole(Role roles, User user) {
        this.roles = roles;
        this.user = user;
        this.userRoleId = new UserRoleId(
                user.getId(),
                roles.getId()
        );
    }

    public Role getRoles() {
        return roles;
    }

    public User getUser() {
        return user;
    }
}

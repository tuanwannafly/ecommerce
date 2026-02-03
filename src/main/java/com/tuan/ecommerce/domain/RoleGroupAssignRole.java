package com.tuan.ecommerce.domain;


import java.time.LocalDateTime;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity
@Table(name = "ROLE_GROUP_ASSIGN_ROLE")
public class RoleGroupAssignRole {

    @EmbeddedId
    private RoleGroupAssignRoleId roleGroupAssignRoleId;


    @ManyToOne
    @MapsId("roleGroupId")
    @JoinColumn(name = "ROLE_GROUP_ID", nullable = false)
    private RoleGroup roleGroup;


    @ManyToOne
    @MapsId("roleId")
    @JoinColumn(name = "ROLE_ID", nullable = false)
    private Role role;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private int isDeleted = 0;

    public RoleGroupAssignRole(LocalDateTime createdAt, Role role, RoleGroup roleGroup, RoleGroupAssignRoleId roleGroupAssignRoleId, LocalDateTime updatedAt) {
        this.createdAt = createdAt;
        this.role = role;
        this.roleGroup = roleGroup;
        this.roleGroupAssignRoleId = roleGroupAssignRoleId;
        this.updatedAt = updatedAt;
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public RoleGroupAssignRoleId getRoleGroupAssignRoleId() {
        return roleGroupAssignRoleId;
    }

    public void setRoleGroupAssignRoleId(RoleGroupAssignRoleId roleGroupAssignRoleId) {
        this.roleGroupAssignRoleId = roleGroupAssignRoleId;
    }

    public RoleGroup getRoleGroup() {
        return roleGroup;
    }

    public void setRoleGroup(RoleGroup roleGroup) {
        this.roleGroup = roleGroup;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public int getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(int isDeleted) {
        this.isDeleted = isDeleted;
    }

    


}

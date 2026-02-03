package com.tuan.ecommerce.domain;

import java.time.LocalDateTime;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity
@Table(name = "ACCOUNT_ASSIGN_ROLE_GROUP")
public class AccountAssignRoleGroup {
    @EmbeddedId
    private AccountAssignRoleGroupId accountAssignRoleGroupId;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("accountId")
    @JoinColumn(name = "ACCOUNT_ID", nullable = false)
    private Account account;


    @ManyToOne
    @MapsId("roleGroupId")
    @JoinColumn(name = "ROLE_GROUP_ID", nullable = false)
    private RoleGroup roleGroup;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private int isDeleted = 0;

    public AccountAssignRoleGroup(Account account, AccountAssignRoleGroupId accountAssignRoleGroupId, LocalDateTime createdAt, RoleGroup roleGroup, LocalDateTime updatedAt) {
        this.account = account;
        this.accountAssignRoleGroupId = accountAssignRoleGroupId;
        this.createdAt = createdAt;
        this.roleGroup = roleGroup;
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

    public AccountAssignRoleGroupId getAccountAssignRoleGroupId() {
        return accountAssignRoleGroupId;
    }

    public void setAccountAssignRoleGroupId(AccountAssignRoleGroupId accountAssignRoleGroupId) {
        this.accountAssignRoleGroupId = accountAssignRoleGroupId;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public RoleGroup getRoleGroup() {
        return roleGroup;
    }

    public void setRoleGroup(RoleGroup roleGroup) {
        this.roleGroup = roleGroup;
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

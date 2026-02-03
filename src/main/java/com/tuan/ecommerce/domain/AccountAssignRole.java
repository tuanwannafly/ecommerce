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
@Table(name = "ACCOUNT_ASSIGN_ROLE")
public class AccountAssignRole {

    @EmbeddedId
    private AccountAssignRoleId accountAssignRoleId;

    @ManyToOne
    @MapsId("accountId")
    @JoinColumn(name = "ACCOUNT_ID", nullable = false)
    private Account account;


    @ManyToOne
    @MapsId("roleId")
    @JoinColumn(name = "ROLE_ID", nullable = false)
    private Role role;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private int isDeleted = 0;

    public AccountAssignRole(Account account, AccountAssignRoleId accountAssignRoleId, LocalDateTime createdAt, Role role, LocalDateTime updatedAt) {
        this.account = account;
        this.accountAssignRoleId = accountAssignRoleId;
        this.createdAt = createdAt;
        this.role = role;
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

    public AccountAssignRoleId getAccountAssignRoleId() {
        return accountAssignRoleId;
    }

    public void setAccountAssignRoleId(AccountAssignRoleId accountAssignRoleId) {
        this.accountAssignRoleId = accountAssignRoleId;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
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

package com.tuan.ecommerce.domain;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity
@Table(name = "ROLE")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;

    @ManyToOne
    @JoinColumn(name = "function_id")
    private Functions function;

    private int addPerm = 0;

    private int editPerm = 0;

    private int deletePerm = 0;

    private int downloadPerm = 0;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private int isDeleted = 0;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Functions getFunction() {
        return function;
    }

    public void setFunction(Functions function) {
        this.function = function;
    }

    public int getAddPerm() {
        return addPerm;
    }

    public void setAddPerm(int addPerm) {
        this.addPerm = addPerm;
    }

    public int getEditPerm() {
        return editPerm;
    }

    public void setEditPerm(int editPerm) {
        this.editPerm = editPerm;
    }

    public int getDeletePerm() {
        return deletePerm;
    }

    public void setDeletePerm(int deletePerm) {
        this.deletePerm = deletePerm;
    }

    public int getDownloadPerm() {
        return downloadPerm;
    }

    public void setDownloadPerm(int downloadPerm) {
        this.downloadPerm = downloadPerm;
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

package com.tuan.ecommerce.domain.RBAC;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import com.tuan.ecommerce.domain.enumeration.Status;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "users",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = "email")
    }
)
public class  User extends BaseEntity{



    private String password;

    private String email;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private Integer isDeleted = 0;

    private String phone;

    @Enumerated(EnumType.STRING)
    private Status status = Status.ACTIVE;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UserRole> userRole = new HashSet<>();

    public User() {

    }

    public User(String email, String password, String phone) {
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.status = Status.ACTIVE;
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

    public void lock() {
        if(this.status == Status.INACTIVE) {
            throw  new IllegalStateException("User already locked");
        }
        this.status = Status.INACTIVE;
    }

    public void activate() {
        if(this.status == Status.ACTIVE) {
            throw  new IllegalStateException("User already active");
        }
        this.status = Status.ACTIVE;
    }

    public void assignRole(Role role) {
        if(role == null) {
            throw  new IllegalStateException("Role cannot be null");
        }

        boolean alreadyAssigned = this.userRole.stream()
                .anyMatch(ur -> ur.getRole().getId().equals(role.getId()));
        if(alreadyAssigned) {
            throw  new IllegalStateException("User already has this role");
        }
        this.userRole.add(new UserRole(role, this));

    }

    public void removeRole(Role role) {
        UserRole target = userRole.stream()
            .filter(ur -> ur.getRole().getId().equals(role.getId()))
            .findFirst()
            .orElseThrow(() -> 
                new IllegalStateException("User does not have this role")
            );

        userRole.remove(target);
    }







    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
    }


    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }


    public String getPhone() {
        return phone;
    }


    public void setPhone(String phone) {
        this.phone = phone;
    }


    public Status getStatus() {
        return status;
    }

    public Set<UserRole> getUserRoles() {
        return userRole;
    }

    


}

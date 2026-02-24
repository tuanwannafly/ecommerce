package com.tuan.ecommerce.domain.DTO;

import java.util.Set;
import java.util.stream.Collectors;

import com.tuan.ecommerce.domain.RBAC.User;
import com.tuan.ecommerce.domain.enumeration.Status;

public class UserResponse {
     private Long id;

    private String email;

    private Status status;
    private Set<String> roleName;

    public UserResponse(Long id, String email, Status status, Set<String> roleName) {
        this.email = email;
        this.id = id;
        this.status = status;
        this.roleName = roleName;
    }

    





    public UserResponse(Long id, String email, Status status) {
        this.id = id;
        this.email = email;
        this.status = status;
    }







    public String getEmail() {
        return email;
    }

    
    public static UserResponse from(User user) {

        Set<String> roles = user.getUserRoles()
                .stream()
                .map(ur -> ur.getRole().getName())
                .collect(Collectors.toSet());

        return new UserResponse(
                user.getId(),
                user.getEmail(),
                user.getStatus(),
                roles
        );
    }

    public Long getId() {
        return id;
    }

    public Status getStatus() {
        return status;
    }
    public Set<String> getRole() {
        return roleName;
    }
}



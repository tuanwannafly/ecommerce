package com.tuan.ecommerce.domain.DTO;

import java.util.Set;
import java.util.stream.Collectors;

import com.tuan.ecommerce.domain.RBAC.Role;

public class RoleResponse {
    private Long id;
    private String name;
    private String description;

    private Set<String> permissionName;
    
    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public RoleResponse(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public RoleResponse(Long id, String name, String description, Set<String> permissionName) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.permissionName = permissionName;
    }
    public static RoleResponse from(Role role) {
        Set<String> permisisons = role.getRolePermission()
            .stream()
            .map(ur -> ur.getPermission().getName())
            .collect(Collectors.toSet());

        return new RoleResponse(
            role.getId(),
            role.getName(),
            role.getDescription(),
            permisisons
        );
    }
    
}

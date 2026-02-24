package com.tuan.ecommerce.domain.RBAC;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "role")
public class Role extends BaseEntity{


    @OneToMany(mappedBy="role",cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<RolePermission> rolePermission = new HashSet<>();

    public  Role() {

    }

    public Role(Set<RolePermission> rolePermission) {
        this.rolePermission = rolePermission;
    }

    public Role(Set<RolePermission> rolePermission, Long id, String name, String description) {
        super(id, name, description);
        this.rolePermission = rolePermission;
    }

    public Set<RolePermission> getRolePermission() {
        return rolePermission;
    }

    public void removePermission(Permission permission) {
        RolePermission target = rolePermission.stream()
            .filter(ur -> ur.getPermission().getPermissionId().equals(permission.getPermissionId()))
            .findFirst()
            .orElseThrow(() ->
                new IllegalStateException("Role does not have this permission")
            );
        rolePermission.remove(target);
    }

    public void addPermission(Permission permission) {
        if (permission == null) {
            throw new IllegalArgumentException("Permission cannot be null");
        }
        this.rolePermission.add(new RolePermission(permission, this));
    }

    

    

}

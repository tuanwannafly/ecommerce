package com.tuan.ecommerce.domain.RBAC;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "role")
public class Role extends BaseEntity{


    @OneToMany(mappedBy="role")
    private Set<RolePermission> rolePermission;

    protected  Role() {

    }

}

package com.tuan.ecommerce.domain.DTO;

import jakarta.validation.constraints.NotNull;

public class AssignRoleRequest {
    @NotNull
    private Long roleId;

    public AssignRoleRequest() {

    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
    
}

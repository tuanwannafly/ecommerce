package com.tuan.ecommerce.domain.DTO;
import jakarta.validation.constraints.NotNull;

public class AssignPermissionRequest {
    @NotNull
    private Long permissionId;

    public AssignPermissionRequest() {

    }

    public Long getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
    }
}

package com.tuan.ecommerce.service;

import org.springframework.data.domain.Page;

import com.tuan.ecommerce.domain.DTO.CreateRoleRequest;
import com.tuan.ecommerce.domain.DTO.RoleResponse;

public interface  RoleService {
    Page<RoleResponse> getAllRole(int page, int size);
    RoleResponse getRoleById(Long roleId);
    RoleResponse createRole(CreateRoleRequest role);
    RoleResponse updateRole(Long id, CreateRoleRequest roleDetail);
    RoleResponse assignPermission(Long roleId, Long permissionId);
}

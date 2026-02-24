package com.tuan.ecommerce.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tuan.ecommerce.domain.DTO.AssignPermissionRequest;
import com.tuan.ecommerce.domain.DTO.CreateRoleRequest;
import com.tuan.ecommerce.domain.DTO.RoleResponse;
import com.tuan.ecommerce.service.RoleService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/roles")
public class RoleController {
    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PreAuthorize("hasAuthority('ROLE_VIEW_ALL')")
    @GetMapping
    public ResponseEntity<Page<RoleResponse>> getAllRoles(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
            Page<RoleResponse> roleResponses = roleService.getAllRole(page, size);
            return ResponseEntity.ok(roleResponses);
    }

    @PreAuthorize("hasAuthority('ROLE_CREATE')")
    @PostMapping
    public ResponseEntity<RoleResponse> createRole(@Valid @RequestBody CreateRoleRequest request) {
        RoleResponse role = roleService.createRole(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(role);
    }

    @PreAuthorize("hasAuthority('ROLE_VIEW')")
    @GetMapping("/{id}")
    public ResponseEntity<RoleResponse> getRoleById(@PathVariable("id") Long roleId) {
        RoleResponse role = roleService.getRoleById(roleId);
        return ResponseEntity.ok(role);
    }

    @PreAuthorize("hasAuthority('ROLE_ASSIGN_PERMISSION')")
    @PostMapping("/{id}/permissions")
    public ResponseEntity<RoleResponse> assignPermission(@PathVariable Long id,@Valid @RequestBody AssignPermissionRequest request) {
        RoleResponse updated = roleService.assignPermission(id, request.getPermissionId());
        return ResponseEntity.ok(updated);
    }

}

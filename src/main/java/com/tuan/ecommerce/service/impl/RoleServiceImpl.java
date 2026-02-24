package com.tuan.ecommerce.service.impl;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tuan.ecommerce.config.UserAlreadyExistsException;
import com.tuan.ecommerce.domain.DTO.CreateRoleRequest;
import com.tuan.ecommerce.domain.DTO.RoleResponse;
import com.tuan.ecommerce.domain.RBAC.Permission;
import com.tuan.ecommerce.domain.RBAC.Role;
import com.tuan.ecommerce.repository.PermissionRepository;
import com.tuan.ecommerce.repository.RoleRepository;
import com.tuan.ecommerce.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService{
    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;

    



    public RoleServiceImpl(RoleRepository roleRepository, PermissionRepository permissionRepository) {
        this.roleRepository = roleRepository;
        this.permissionRepository = permissionRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<RoleResponse> getAllRole(int page, int size) {
        Page<Role> roles = roleRepository.findAll(PageRequest.of(page, size));
        return roles.map(RoleResponse::from);
    }

    @Override
    @Transactional(readOnly = true)
    public RoleResponse getRoleById(Long roleId) {
        Optional<Role> role = roleRepository.findById(roleId);
        return RoleResponse.from(role.get());
    }

    @Override
    public RoleResponse createRole(CreateRoleRequest role) {
        if(roleRepository.existsByName(role.getName())) {
            throw new UserAlreadyExistsException("Role already exists");
        }
        Role role1 = new Role();
        role1.setName(role.getName());
        role1.setDescription(role.getDescription());
        roleRepository.save(role1);
        return RoleResponse.from(role1);
    }

    `@Override`
    `@Transactional`
    public RoleResponse updateRole(Long id, CreateRoleRequest roleDetail) {
        Role role = roleRepository.findById(id)
            .orElseThrow(() -> new NoSuchElementException("Role not found with id: " + id));
        role.setName(roleDetail.getName());
        role.setDescription(roleDetail.getDescription());
        roleRepository.save(role);
        return RoleResponse.from(role);

    }



    @Override
    @Transactional
    public RoleResponse assignPermission(Long roleId, Long permissionId) {
        Role role = roleRepository.findById(roleId)
            .orElseThrow(() -> new NoSuchElementException("Role not found"));
        Permission permission = permissionRepository.findById(permissionId)
            .orElseThrow(() -> new NoSuchElementException("Permisison not found"));
        role.addPermission(permission);
        return RoleResponse.from(role);
    }

    
    
    
}

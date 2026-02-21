package com.tuan.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tuan.ecommerce.domain.RBAC.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    
}

package com.tuan.ecommerce.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tuan.ecommerce.domain.RBAC.Permission;

public interface  PermissionRepository extends JpaRepository<Permission, Long>{
    Optional<Permission> findByName(String name);
}

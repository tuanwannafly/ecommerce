package com.tuan.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tuan.ecommerce.domain.RBAC.User;

public interface  UserRepository extends JpaRepository<User, Long>{
    boolean existsByEmail(String email);
}

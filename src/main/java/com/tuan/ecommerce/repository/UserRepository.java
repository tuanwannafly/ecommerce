package com.tuan.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tuan.ecommerce.domain.RBAC.User;

public interface  UserRepository extends JpaRepository<User, Long>{
    boolean existsByEmail(String email);
    User findByEmail(String email);
        @Query("""
            SELECT u FROM User u
            LEFT JOIN FETCH u.userRole ur
            LEFT JOIN FETCH ur.role r
            LEFT JOIN FETCH r.rolePermission rp
            LEFT JOIN FETCH rp.permission
            WHERE u.email = :email
        """)
        User findUserWithRoles(@Param("email") String email);
}

package com.tuan.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tuan.ecommerce.domain.RBAC.UserRole;
import com.tuan.ecommerce.domain.RBAC.UserRoleId;

public interface  UserRoleRepository extends JpaRepository<UserRole, UserRoleId>{
    // @Modifying
    // @Query("""
    //     DELETE FROM user_role ur
    //     WHERE ur.user.id = :userId
    //     AND ur.role.id = :roleId
    //     """)
    // void deleteUserRole(Long userId, Long roleId);

}

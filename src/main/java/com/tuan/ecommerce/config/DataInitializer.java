package com.tuan.ecommerce.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.tuan.ecommerce.domain.RBAC.Permission;
import com.tuan.ecommerce.domain.RBAC.Role;
import com.tuan.ecommerce.repository.PermissionRepository;
import com.tuan.ecommerce.repository.RoleRepository;

@Component
public class DataInitializer implements CommandLineRunner{
    private final PermissionRepository permissionRepository;
    private final RoleRepository roleRepository;

    public DataInitializer(PermissionRepository permissionRepository,
                           RoleRepository roleRepository) {
        this.permissionRepository = permissionRepository;
        this.roleRepository = roleRepository;
    }

     @Override
    public void run(String... args) {
        System.out.println("🔥 DataInitializer is running...");

        Permission createUser = createPermissionIfNotExists("CREATE_USER");
        Permission viewUser = createPermissionIfNotExists("VIEW_USER");
        Permission viewAll = createPermissionIfNotExists("VIEW_ALL_USERS");

        Role admin = createRoleIfNotExists("ROLE_ADMIN");

        // admin.addPermission(createUser);
        // admin.addPermission(viewUser);
        // admin.addPermission(viewAll);
        seedUserPermissions();
        seedRolePermissions();

        roleRepository.save(admin);
        System.out.println("✅ Done seeding data");
    }



    private Permission createPermissionIfNotExists(String name) {
        return permissionRepository.findByName(name)
                .orElseGet(() -> {
                    Permission p = new Permission();
                    p.setName(name);
                    return permissionRepository.save(p);
                });
    }

    private Role createRoleIfNotExists(String name) {
        return roleRepository.findByName(name)
                .orElseGet(() -> {
                    Role role = new Role();
                    role.setName(name);
                    return roleRepository.save(role);
                });
    }

    private void seedUserPermissions() {
        createPermissionIfNotExists("USER_CREATE");
        createPermissionIfNotExists("USER_VIEW");
        createPermissionIfNotExists("USER_VIEW_ALL");
        createPermissionIfNotExists("USER_UPDATE");
        createPermissionIfNotExists("USER_ACTIVATE");
        createPermissionIfNotExists("USER_LOCK");
        createPermissionIfNotExists("USER_ASSIGN_ROLE");
        createPermissionIfNotExists("USER_REMOVE_ROLE");
    }

    private void seedRolePermissions() {
        createPermissionIfNotExists("ROLE_CREATE");
        createPermissionIfNotExists("ROLE_VIEW");
        createPermissionIfNotExists("ROLE_VIEW_ALL");
    }

}

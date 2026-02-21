package com.tuan.ecommerce.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tuan.ecommerce.config.NoSuchUserExistsException;
import com.tuan.ecommerce.config.UserAlreadyExistsException;
import com.tuan.ecommerce.domain.DTO.CreateUserRequest;
import com.tuan.ecommerce.domain.DTO.PatchUserRequest;
import com.tuan.ecommerce.domain.DTO.UserResponse;
import com.tuan.ecommerce.domain.RBAC.Role;
import com.tuan.ecommerce.domain.RBAC.User;
import com.tuan.ecommerce.repository.RoleRepository;
import com.tuan.ecommerce.repository.UserRepository;
import com.tuan.ecommerce.repository.UserRoleRepository;
import com.tuan.ecommerce.service.UserService;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final UserRoleRepository userRoleRepository;





    
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder,
            RoleRepository roleRepository, UserRoleRepository userRoleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.userRoleRepository = userRoleRepository;
    }

    @Transactional(readOnly = true)
    public Page<UserResponse> getAllUser(int page, int size) {
        Page<User> users = userRepository.findAll(PageRequest.of(page, size)); 

        return users.map(UserResponse::from);
    }

    @Override
    public UserResponse getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new NoSuchUserExistsException("User not found" + id));
        return UserResponse.from(user);
        
    }

    @Override
    @Transactional
    public UserResponse CreateUser(CreateUserRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new UserAlreadyExistsException("Email already exists");
        }

        String encodedPassword = passwordEncoder.encode(request.getPassword());

        User user = new User(request.getEmail(), encodedPassword, request.getPhone());
        user.setName(request.getFullName());
        user.setPhone(request.getPhone());

        try {
            userRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            throw new UserAlreadyExistsException("Email already exists");
        }
        return UserResponse.from(user);
    }


    @Override
    @Transactional
    public UserResponse UpdateUser(Long id, CreateUserRequest userDetail) {
        User request = userRepository.findById(id)
                .orElseThrow(() -> new NoSuchUserExistsException("User not found: " + id));
        request.setEmail(userDetail.getEmail());
        request.setName(userDetail.getFullName());
        request.setPhone(userDetail.getPhone());
        if(userDetail.getPassword() != null) {
            request.setPassword(passwordEncoder.encode(userDetail.getPassword()));
        }
        userRepository.save(request);
        return UserResponse.from(request);
    }


    @Override
    @Transactional
    public UserResponse patchUser(Long id, PatchUserRequest request) {
        User user = userRepository.findById(id).orElseThrow(() -> new NoSuchUserExistsException("User not found" + id));
        if(request.getEmail() != null) {
            
                if(userRepository.existsByEmail(request.getEmail())) {
                     throw new UserAlreadyExistsException("Email already exists");
                }
                user.setEmail(request.getEmail());
            

        }
        if (request.getFullName() != null) {
                user.setName(request.getFullName());
        }

        if (request.getPhone() != null) {
            user.setPhone(request.getPhone());
        }

        if (request.getPassword() != null) {
            String encoded = passwordEncoder.encode(request.getPassword());
            user.setPassword(encoded);
        }

        userRepository.save(user);
        UserResponse userResponse = new UserResponse(id, request.getEmail() , user.getStatus());
        return userResponse;
    }


    @Override
    @Transactional
    public UserResponse lockUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new NoSuchUserExistsException("User not found"));
        user.lock();
         userRepository.save(user);
         return UserResponse.from(user);
    }


    @Override
    @Transactional
    public UserResponse activateUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new NoSuchUserExistsException("User not found"));
        user.activate();
         userRepository.save(user);
         return UserResponse.from(user);
    }


    @Override
    @Transactional
    public UserResponse assignRole(Long userId, Long roleId) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new NoSuchUserExistsException("User not found"));

        Role role = roleRepository.findById(roleId)
            .orElseThrow(() -> new NoSuchElementException("Role not found"));
        user.assignRole(role);

        userRepository.save(user);
        return UserResponse.from(user);
    }

    @Override
    @Transactional
    public void removeRole(Long userId, Long roleId) {
        User user = userRepository.findById(userId)
        .orElseThrow(() -> new NoSuchUserExistsException("User not found"));
    
        Role role = roleRepository.findById(roleId)
        .orElseThrow(() -> new RuntimeException("Role not found"));
        user.removeRole(role);
        userRoleRepository.deleteUserRole(userId, roleId);
    }

    @Override
    public List<String> getUserRoles(Long id) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new NoSuchUserExistsException("User not found"));

    return user.getUserRoles().stream()
            .map(ur -> ur.getRoles().getName())
            .toList();
        
    }
    
}

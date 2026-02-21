package com.tuan.ecommerce.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.tuan.ecommerce.domain.DTO.CreateUserRequest;
import com.tuan.ecommerce.domain.DTO.PatchUserRequest;
import com.tuan.ecommerce.domain.DTO.UserResponse;

public interface  UserService {


    Page<UserResponse> getAllUser(int page, int size);
    UserResponse getUserById(Long userId);
    UserResponse createUser(CreateUserRequest user);
    UserResponse updateUser(Long id, CreateUserRequest userDetail);
    UserResponse patchUser(Long id, PatchUserRequest request);
    UserResponse lockUser(Long id);
    UserResponse activateUser(Long id);
    UserResponse assignRole(Long userId, Long roleId);
    void removeRole(Long userId, Long roleId);
    List<String> getUserRoles(Long id);
    

}

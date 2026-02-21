package com.tuan.ecommerce.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.tuan.ecommerce.config.ErrorResponse;
import com.tuan.ecommerce.config.NoSuchUserExistsException;
import com.tuan.ecommerce.config.UserAlreadyExistsException;
import com.tuan.ecommerce.domain.DTO.AssignRoleRequest;
import com.tuan.ecommerce.domain.DTO.CreateUserRequest;
import com.tuan.ecommerce.domain.DTO.PatchUserRequest;
import com.tuan.ecommerce.domain.DTO.UserResponse;
import com.tuan.ecommerce.service.UserService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;
    
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping
    public ResponseEntity<Page<UserResponse>> getAllUsers(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<UserResponse> userResponses  = userService.getAllUser(page, size);
        return  ResponseEntity.ok(userResponses);
    }
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable("id") Long userId) {
        UserResponse user = userService.getUserById(userId);
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody CreateUserRequest request) {
        UserResponse user = userService.CreateUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable Long id,@Valid @RequestBody CreateUserRequest request) {
        UserResponse user = userService.UpdateUser(id, request);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserResponse> patchUser(@PathVariable Long id,@Valid @RequestBody PatchUserRequest request) {
        UserResponse user = userService.patchUser(id, request);
        return ResponseEntity.ok(user);
    }

    @PatchMapping("/{id}/lock")
    public ResponseEntity<UserResponse> lockUser(@PathVariable Long id) {
        UserResponse user = userService.lockUser(id);
        return ResponseEntity.ok(user);
    }

    @PatchMapping("/{id}/activate")
    public ResponseEntity<UserResponse> activateUser(@PathVariable Long id) {
        UserResponse user = userService.activateUser(id);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/{id}/roles")
    public ResponseEntity<Void> assignRole(@PathVariable Long id, @RequestBody AssignRoleRequest request) {
        userService.assignRole(id, request.getRoleId());
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("/{id}/roles/{roleId}")
    public ResponseEntity<Void> removeRole(@PathVariable Long id, @PathVariable Long roleId) {
        userService.removeRole(id, roleId);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/{id}/roles")
    public ResponseEntity<List<String>> getUserRoles(@PathVariable Long id) {
        List<String> roles = userService.getUserRoles(id);
        return ResponseEntity.ok(roles);
    }

    @ExceptionHandler(value = NoSuchUserExistsException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleNoSuchUserExistsException(NoSuchUserExistsException ex) {
        return new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
    }

    @ExceptionHandler(value = UserAlreadyExistsException .class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleUserExistsException(UserAlreadyExistsException  ex) {
        return new ErrorResponse(HttpStatus.CONFLICT.value(), ex.getMessage());
    }

    @ExceptionHandler(value = NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleNoSuchElementException(NoSuchElementException ex) {
        return new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
    }

}

package com.tuan.ecommerce.domain.DTO;

import jakarta.validation.constraints.Email;

public class PatchUserRequest {
    @Email
    private String email;

    private String fullName;

    private String phone;

    private String password;

    public PatchUserRequest() {}

    public String getEmail() {
        return email;
    }

    public String getFullName() {
        return fullName;
    }

    public String getPhone() {
        return phone;
    }

    public String getPassword() {
        return password;
    }

    
}

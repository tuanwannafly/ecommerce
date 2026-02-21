package com.tuan.ecommerce.domain.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CreateUserRequest {
    @NotBlank
    @Email
    private String email;

    private String fullName;

    @NotBlank
    @Size(min = 6)
    private String password;

    private String phone;


    public CreateUserRequest(String email,String fullName, String password, String phone) {
        this.email = email;
        this.fullName = fullName;
        this.password = password;
        this.phone = phone;
    }

    


    public CreateUserRequest(@NotBlank @Email String email, String fullName, String phone) {
        this.email = email;
        this.fullName = fullName;
        this.phone = phone;
    }




    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getFullName() {
        return fullName;
    }

    public CreateUserRequest() {}





}

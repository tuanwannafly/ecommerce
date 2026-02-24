package com.tuan.ecommerce.domain.DTO;

public class CreateRoleRequest {
    private String name;
    private String description;
    
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public CreateRoleRequest(String name, String description) {
        this.name = name;
        this.description = description;
    }

    
}

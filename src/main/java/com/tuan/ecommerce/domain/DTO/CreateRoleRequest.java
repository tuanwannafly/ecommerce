package com.tuan.ecommerce.domain.DTO;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CreateRoleRequest {
    private String name;
    private String description;
    
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    @JsonCreator
    public CreateRoleRequest(
            @JsonProperty("name") String name,
            @JsonProperty("description") String description) {
        this.name = name;
        this.description = description;
    }
}


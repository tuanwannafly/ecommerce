package com.tuan.ecommerce.domain.RBAC;

import java.io.Serializable;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter 
@Setter
public abstract class BaseEntity implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected  Long id;

    protected  String name;

    protected  String description;

    public BaseEntity() {
    }

    public BaseEntity(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    


    


}

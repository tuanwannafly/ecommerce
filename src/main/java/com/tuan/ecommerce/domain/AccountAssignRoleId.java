package com.tuan.ecommerce.domain;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class AccountAssignRoleId implements Serializable{
    
    @Column(name = "ACCOUNT_ID")
    private Long accountId;

    @Column(name = "ROLE_ID")
    private Long roleId;
}

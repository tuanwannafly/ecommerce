package com.tuan.ecommerce.domain;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class AccountAssignRoleGroupId implements Serializable{
    
    @Column(name = "ACCOUNT_ID")
    private Long accountId;

    @Column(name = "ROLE_GROUP_ID")
    private Long roleGroupId;
}

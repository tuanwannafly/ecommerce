package com.tuan.ecommerce.domain;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class RoleGroupAssignRoleId implements Serializable {

    @Column(name = "ROLE_GROUP_ID")
    private Long roleGroupId;

    @Column(name = "ROLE_ID")
    private Long roleId;
}

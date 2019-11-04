package com.ike.pojo;

public class RolePermission {
    private Long id;

    private Long rpRoleId;

    private Long rpPermissionId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRpRoleId() {
        return rpRoleId;
    }

    public void setRpRoleId(Long rpRoleId) {
        this.rpRoleId = rpRoleId;
    }

    public Long getRpPermissionId() {
        return rpPermissionId;
    }

    public void setRpPermissionId(Long rpPermissionId) {
        this.rpPermissionId = rpPermissionId;
    }
}
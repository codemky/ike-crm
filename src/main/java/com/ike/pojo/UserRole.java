package com.ike.pojo;

public class UserRole {
    private Long id;

    private Long urUserId;

    private Long urRoleId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUrUserId() {
        return urUserId;
    }

    public void setUrUserId(Long urUserId) {
        this.urUserId = urUserId;
    }

    public Long getUrRoleId() {
        return urRoleId;
    }

    public void setUrRoleId(Long urRoleId) {
        this.urRoleId = urRoleId;
    }
}
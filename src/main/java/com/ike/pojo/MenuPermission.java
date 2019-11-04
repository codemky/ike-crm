package com.ike.pojo;

public class MenuPermission {
    private Long id;

    private Long mpMenuId;

    private Long mpPermissionId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMpMenuId() {
        return mpMenuId;
    }

    public void setMpMenuId(Long mpMenuId) {
        this.mpMenuId = mpMenuId;
    }

    public Long getMpPermissionId() {
        return mpPermissionId;
    }

    public void setMpPermissionId(Long mpPermissionId) {
        this.mpPermissionId = mpPermissionId;
    }
}
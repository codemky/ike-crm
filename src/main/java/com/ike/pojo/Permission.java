package com.ike.pojo;

public class Permission {
    private Long id;

    private String permissionName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName == null ? null : permissionName.trim();
    }

    @Override
    public String toString() {
        return "Permission{" +
                       "id=" + id +
                       ", permissionName='" + permissionName + '\'' +
                       '}';
    }
}
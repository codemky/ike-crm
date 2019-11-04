package com.ike.pojo.vo;

import com.ike.pojo.Permission;
import com.ike.pojo.Role;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RoleVo extends Role {

    //权限集合
    private List<Permission> permissions = new ArrayList<>();

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    @Override
    public String toString() {
        return "RoleVo{" +
                       "permissions=" + permissions +
                       '}';
    }
}

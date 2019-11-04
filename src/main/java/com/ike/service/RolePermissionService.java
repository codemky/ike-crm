package com.ike.service;

import com.ike.pojo.Permission;
import com.ike.pojo.Role;
import com.ike.pojo.RolePermission;
import com.ike.pojo.vo.RoleVo;

import java.util.List;

public interface RolePermissionService {
    public List<RoleVo> listByRid(List<Long> ids);

    public int batchInsert(List<Long> permissions, Long rid);

    public int batchDelete(List<Long> permissions, Long rid);

    public List<Long> listPerissionIdByRid(Long id);

    public List<Permission> listAllPermission();
}

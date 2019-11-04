package com.ike.service.impl;

import com.ike.mapper.ext.RolePermissinoExtMapper;
import com.ike.pojo.Permission;
import com.ike.pojo.vo.RoleVo;
import com.ike.service.RolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RolePermissinoServiceImpl implements RolePermissionService {

    @Autowired
    private RolePermissinoExtMapper rolePermissinoExtMapper;

    @Override
    public List<RoleVo> listByRid(List<Long> ids) {
        return rolePermissinoExtMapper.listByRid(ids);
    }

    @Override
    @Transactional
    public int batchInsert(List<Long> permissions, Long rid) {
        return rolePermissinoExtMapper.batchInsert(permissions, rid);
    }

    @Override
    @Transactional
    public int batchDelete(List<Long> permissions, Long rid) {
        return rolePermissinoExtMapper.batchDelete(permissions, rid);
    }

    @Override
    public List<Long> listPerissionIdByRid(Long id) {
        return rolePermissinoExtMapper.listPerissionIdByRid(id);
    }

    @Override
    public List<Permission> listAllPermission() {
        return rolePermissinoExtMapper.listAllPermission();
    }
}

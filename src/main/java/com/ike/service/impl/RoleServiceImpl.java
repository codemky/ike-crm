package com.ike.service.impl;

import com.ike.mapper.RoleMapper;
import com.ike.mapper.ext.RoleExtMapper;
import com.ike.pojo.Role;
import com.ike.pojo.vo.RoleVo;
import com.ike.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private RoleExtMapper roleExtMapper;

    @Override
    public int insert(Role role) {
        return roleMapper.insert(role);
    }

    @Override
    public int update(Role role) {
        return roleMapper.updateByPrimaryKeySelective(role);
    }

    @Override
    public int deleteById(Long id) {
        return roleMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<Role> selectAll() {
        return roleMapper.selectByExample(null);
    }

    @Override
    public List<Role> listRolesByUid(Long id) {
        return roleExtMapper.listRolesByUid(id);
    }

    @Override
    @Transactional
    public int batchInsert(List<Long> roles, Long id) {
        return roleExtMapper.batchInsert(roles, id);
    }

    @Override
    @Transactional
    public int batchDelete(List<Long> roles, Long id) {
        return roleExtMapper.batchDelete(roles, id);
    }
}

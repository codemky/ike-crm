package com.ike.service;

import com.ike.pojo.Role;
import com.ike.pojo.vo.RoleVo;

import java.util.List;

public interface RoleService {
    public int insert(Role role);

    public int update(Role role);

    public int deleteById(Long id);

    public List<Role> selectAll();

    public List<Role> listRolesByUid(Long id);

    public int batchInsert(List<Long> roles, Long id);

    public int batchDelete(List<Long> roles, Long id);

}

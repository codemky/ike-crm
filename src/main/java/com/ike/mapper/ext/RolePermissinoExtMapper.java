package com.ike.mapper.ext;

import com.ike.pojo.Permission;
import com.ike.pojo.Role;
import com.ike.pojo.RolePermission;
import com.ike.pojo.vo.RoleVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RolePermissinoExtMapper {
    /**
     * 根据id查询对应角色所有权限
     *
     * @param ids
     * @return
     */
    public List<RoleVo> listByRid(@Param("ids") List<Long> ids);

    /**
     * 根据Id批量插入指定角色的权限
     *
     * @param permissions
     * @param rid
     * @return
     */
    public int batchInsert(@Param("permissions") List<Long> permissions, @Param("id") Long rid);

    /**
     * 根据id批量删除指定的角色权限记录
     *
     * @param permissions
     * @param rid
     * @return
     */
    public int batchDelete(@Param("permissions") List<Long> permissions, @Param("id") Long rid);

    /**
     * 根据角色Id获取对应权限的id集合
     *
     * @param id
     * @return
     */
    public List<Long> listPerissionIdByRid(@Param("id") Long id);

    /**
     * 获取权限列表
     *
     * @return
     */
    public List<Permission> listAllPermission();
}

package com.ike.mapper.ext;

import com.ike.pojo.Role;
import com.ike.pojo.vo.RoleVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleExtMapper {

    public List<Role> listRolesByUid(@Param("id") Long id);

    public int batchInsert(@Param("roles") List<Long> roles, @Param("id") Long id);

    public int batchDelete(@Param("roles") List<Long> roles, @Param("id") Long id);

}

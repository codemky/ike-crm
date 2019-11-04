package com.ike.mapper;

import com.ike.pojo.MenuPermission;
import com.ike.pojo.MenuPermissionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MenuPermissionMapper {
    int countByExample(MenuPermissionExample example);

    int deleteByExample(MenuPermissionExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MenuPermission record);

    int insertSelective(MenuPermission record);

    List<MenuPermission> selectByExample(MenuPermissionExample example);

    MenuPermission selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") MenuPermission record, @Param("example") MenuPermissionExample example);

    int updateByExample(@Param("record") MenuPermission record, @Param("example") MenuPermissionExample example);

    int updateByPrimaryKeySelective(MenuPermission record);

    int updateByPrimaryKey(MenuPermission record);
}
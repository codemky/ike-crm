package com.ike.mapper.ext;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ike.pojo.vo.CustomerListVo;
import com.ike.pojo.vo.UserVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserExtMapper {

    /**
     * 获取全部用户列表(多表查询)
     * @return
     */
    public List<UserVo> selectAll();

    /**
     * 根据用户id获取用户信息
     * @param id
     * @return
     */
    public UserVo selectById(@Param("id") Long id);

    /**
     * 根据部门Id查询该部门下的所有员工
     * @param page
     * @param id
     * @return
     */
    IPage<UserVo> selectListByUid(Page page, @Param("id") Long id);

    /**
     * 通过excel批量导入user数据
     *
     * @param userVos
     */
    public void importExcel(@Param("importList") List<UserVo> userVos);

    /**
     * 导出用户信息到excel
     *
     * @return
     */
    public List<UserVo> getExportList();


}
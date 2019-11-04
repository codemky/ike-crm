package com.ike.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ike.pojo.Department;
import com.ike.pojo.vo.UserVo;

import java.util.List;

public interface DepartmentService {
    public List<Department> selectAll();

    public int insert(Department department);

    public int update(Department department) ;

    public int deleteById(Long id);

    public Department selectById(Long id);

    public IPage<UserVo> listAllEmpByDeptId(Page<UserVo> page, Long id);

    public Department selectByDepartmentName(String departmentName);
}

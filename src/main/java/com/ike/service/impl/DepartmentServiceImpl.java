package com.ike.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ike.mapper.DepartmentMapper;
import com.ike.mapper.ext.UserExtMapper;
import com.ike.pojo.Department;
import com.ike.pojo.DepartmentExample;
import com.ike.pojo.vo.UserVo;
import com.ike.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;
    @Autowired
    private UserExtMapper userExtMapper;

    @Override
    public List<Department> selectAll() {
        return departmentMapper.selectByExample(null);
    }

    @Override
    public int insert(Department department) {
        return departmentMapper.insert(department);
    }

    @Override
    public int update(Department department) {
        return departmentMapper.updateByPrimaryKeySelective(department);
    }

    @Override
    public int deleteById(Long id) {
        return departmentMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Department selectById(Long id) {
        return departmentMapper.selectByPrimaryKey(id);
    }

    @Override
    public IPage<UserVo> listAllEmpByDeptId(Page<UserVo> page, Long id) {
        return userExtMapper.selectListByUid(page,id);
    }

    @Override
    public Department selectByDepartmentName(String departmentName) {
        DepartmentExample example = new DepartmentExample();
        example.or().andDepartmentNameEqualTo(departmentName);
        List<Department> departments = departmentMapper.selectByExample(example);
        if (departments.size() == 0) {
            return null;
        }
        return departments.get(0);
    }


}

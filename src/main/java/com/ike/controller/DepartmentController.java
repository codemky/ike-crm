package com.ike.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ike.common.constans.CodeMsg;
import com.ike.common.result.Result;
import com.ike.pojo.Department;
import com.ike.pojo.vo.PageInfo;
import com.ike.pojo.vo.UserVo;
import com.ike.service.DepartmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@Api(value = "部门控制器", description = "部门信息模块(已添加权限配置,需要登陆[Kyre 123456]才能测试)")
@RestController
@RequestMapping("/json/department")
@RequiresAuthentication
@RequiresPermissions(value = "manage_dept:read")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/getDetail")
    @ApiOperation(value = "根据Id获取部门信息(测试通过)", notes = "根据Id获取部门信息")
    public Result<Object> detail(@RequestParam Long id){
        Department department = departmentService.selectById(id);
        return department == null ? Result.error(CodeMsg.ERROR) : Result.success(department);
    }

    @PostMapping("/create")
    @ApiOperation(value = "新增一条部门记录,部门名称唯一(测试通过)", notes = "新增一条部门记录")
    public Result<Object> create(@RequestBody Department department) {
        Department select = departmentService.selectByDepartmentName(department.getDepartmentName());
        if (select != null) {
            return Result.error(new CodeMsg("部门名称已经存在"));
        }

        int insert = departmentService.insert(department);
        return insert > 0 ? Result.success(CodeMsg.SUCCESS) : Result.error(CodeMsg.ERROR);
    }

    @PutMapping("/update")
    @ApiOperation(value = "更新一条部门记录(测试通过)", notes = "更新一条部门记录")
    public Result<Object> update(Department department){
        int update = departmentService.update(department);
        return update > 0 ? Result.success(CodeMsg.SUCCESS) : Result.error(CodeMsg.ERROR);
    }

    @DeleteMapping("/delete")
    @ApiOperation(value = "根据Id删除一条部门记录(测试通过)", notes = "根据Id删除一条部门记录")
    public Result<Object> delete(@RequestParam Long id) {
        int delete = departmentService.deleteById(id);
        return delete > 0 ? Result.success(CodeMsg.SUCCESS) : Result.error(CodeMsg.ERROR);
    }

    @GetMapping("/getAll")
    @ApiOperation(value = "获取部门列表(测试通过)", notes = "获取部门列表")
    public Result listAll(){
        List<Department> list = departmentService.selectAll();
        return list == null ? Result.error(CodeMsg.ERROR) : Result.success(list);
    }

    @GetMapping("/listAllEmpByDid")
    @ApiOperation(value = "根据部门id获取该部门下的员工列表(测试通过)", notes = "获取部门列表")
    public Result<Object> listByDid(@RequestParam("id") Long id,
                                    @RequestParam(value = "pageNum",defaultValue = "1",required = false) Integer pageNum,
                                    @RequestParam(value = "pageSize",defaultValue = "10",required = false) Integer pageSize){
        HashMap<String, Object> map = new HashMap<>();
        Page<UserVo> page = new Page<>(pageNum, pageSize);
        IPage<UserVo> voIPage = departmentService.listAllEmpByDeptId(page, id);
        map.put("pageInfo", PageInfo.getPageVo(voIPage));
        map.put("empList",voIPage.getRecords());
        return Result.success(map);
    }

}

package com.ike.controller;

import com.ike.common.constans.CodeMsg;
import com.ike.common.result.Result;
import com.ike.pojo.Department;
import com.ike.pojo.Role;
import com.ike.pojo.User;
import com.ike.pojo.vo.RoleVo;
import com.ike.pojo.vo.UserVo;
import com.ike.service.RoleService;
import com.ike.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Api(value = "角色控制器", description = "角色信息模块(已添加权限配置,需要登陆[Kyre 123456]才能测试)")
@RestController
@RequestMapping("/json/role")
@RequiresAuthentication
@RequiresPermissions(value = "manage_role:read")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @GetMapping("/getDetail")
    @ApiOperation(value = "根据用户id获取角色信息,一个用户可以有多个角色，用于管理员修改某用户角色时回显(测试通过)", notes = "根据id获取角色信息")
    public Result<Object> detail(@RequestParam Long id) {
        List<Role> roles = roleService.listRolesByUid(id);
        return roles == null ? Result.error(CodeMsg.ERROR) : Result.success(roles);
    }

    @PostMapping("/create")
    @ApiOperation(value = "新增一条角色记录(测试通过)", notes = "新增一条角色记录")
    public Result<Object> create(Role role) {
        int insert = roleService.insert(role);
        return insert > 0 ? Result.success(CodeMsg.SUCCESS) : Result.error(CodeMsg.ERROR);
    }

    @PutMapping("/update")
    @ApiOperation(value = "更新一条角色记录(测试通过)", notes = "更新一条角色记录")
    public Result<Object> update(Role role) {
        int update = roleService.update(role);
        return update > 0 ? Result.success(CodeMsg.SUCCESS) : Result.error(CodeMsg.ERROR);
    }

    @DeleteMapping("/delete")
    @ApiOperation(value = "根据Id删除一条角色记录(测试通过)", notes = "根据Id删除一条角色记录")
    public Result<Object> delete(@RequestBody Long id) {
        int delete = roleService.deleteById(id);
        return delete > 0 ? Result.success(CodeMsg.SUCCESS) : Result.error(CodeMsg.ERROR);
    }

    @GetMapping("/getAll")
    @ApiOperation(value = "获取角色列表,用于管理员为新建用户指定角色时显示(测试通过)", notes = "获取角色列表")
    public Result listAll() {
        List<Role> roles = roleService.selectAll();
        return roles == null ? Result.error(CodeMsg.ERROR) : Result.success(roles);
    }

    @PostMapping("/batchInsert")
    @ApiOperation(value = "根据用户id为其指定角色(适用于新建用户或已有用户)(测试通过)", notes = "批量新增角色记录")
    public Result<Object> batchInsert(@RequestParam Long id, @RequestParam List<Long> newRoleIds) {
        List<Role> roleList = roleService.listRolesByUid(id);
        List<Long> oldRoleIds = roleList.stream().map(Role::getId).collect(Collectors.toList());
        List<String> newRoleIdsStr = newRoleIds.stream().map(a -> String.valueOf(a)).collect(Collectors.toList());
        List<String> oldRoleIdsStr = oldRoleIds.stream().map(a -> String.valueOf(a)).collect(Collectors.toList());
        boolean equals = newRoleIdsStr.stream().collect(Collectors.joining())
                                 .equals(oldRoleIdsStr.stream().collect(Collectors.joining()));

        //用户新角色集合与旧角色集合相同，不操作数据库
        if (equals) {
            return Result.success(CodeMsg.SUCCESS);
        } else {
            //新建用户指定角色
            if (oldRoleIds.size() == 0) {
                int batchInsert = roleService.batchInsert(newRoleIds, id);
                return batchInsert > 0 ? Result.success(CodeMsg.SUCCESS) : Result.error(CodeMsg.ERROR);
            } else {
                //已有用户修改角色
                List<Long> newDifference = newRoleIds.stream().filter(t -> !oldRoleIds.contains(t)).collect(Collectors.toList());
                List<Long> oldDifference = oldRoleIds.stream().filter(t -> !newRoleIds.contains(t)).collect(Collectors.toList());
                List<Long> common = newRoleIds.stream().filter(t -> oldRoleIds.contains(t)).collect(Collectors.toList());

                //新角色集合包含旧角色集合
                if (oldDifference.size() == 0) {
                    int batchInsert = roleService.batchInsert(newDifference, id);
                    return batchInsert > 0 ? Result.success(CodeMsg.SUCCESS) : Result.error(CodeMsg.ERROR);
                } else {
                    //已有用户的角色全更新
                    if (common.size() == 0) {
                        int batchDelete = roleService.batchDelete(oldRoleIds, id);
                        int batchInsert = roleService.batchInsert(newRoleIds, id);
                        return (batchInsert > 0 && batchDelete > 0) ? Result.success(CodeMsg.SUCCESS) : Result.error(CodeMsg.ERROR);
                    } else {
                        //部分更新
                        int batchDelete = roleService.batchDelete(oldDifference, id);
                        int batchInsert = roleService.batchInsert(newDifference, id);
                        return (batchInsert > 0 && batchDelete > 0) ? Result.success(CodeMsg.SUCCESS) : Result.error(CodeMsg.ERROR);
                    }
                }
            }
        }
    }
}

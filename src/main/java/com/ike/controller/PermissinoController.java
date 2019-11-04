package com.ike.controller;

import com.baomidou.mybatisplus.extension.api.R;
import com.ike.common.constans.CodeMsg;
import com.ike.common.result.Result;
import com.ike.pojo.Permission;
import com.ike.pojo.Role;
import com.ike.pojo.RolePermission;
import com.ike.pojo.vo.RoleVo;
import com.ike.service.RolePermissionService;
import com.ike.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Api(value = "权限控制器", description = "权限信息模块:以角色为单位修改权限，再将角色赋给用户(一个用户可以有多个角色)(已添加权限配置,需要登陆[Kyre 123456]才能测试)")
@RestController
@RequestMapping("/json/permission")
@RequiresAuthentication
@RequiresPermissions(value = "manage_role:read")
public class PermissinoController {

    @Autowired
    private RoleService roleService;
    @Autowired
    private RolePermissionService rolePermissionService;


    @GetMapping("/getDetail")
    @ApiOperation(value = "根据角色id集合获取对应权限集合,一个用户可能有多个角色(测试通过)", notes = "根据id获取权限集合")
    public Result<Object> detail(@RequestParam List<Long> ids) {
        Map<String, Object> map = new HashMap<>();
        List<Long> perIds = new ArrayList<>();  //权限id集合
        List<String> perNames = new ArrayList<>(); //权限Name集合
        List<RoleVo> roles = rolePermissionService.listByRid(ids);
        List<List<Permission>> permissions = roles.stream().map(RoleVo::getPermissions).collect(Collectors.toList());

        int fatherSize = permissions.size();
        for (int i = 0; i < fatherSize; i++) {
            int childSize = permissions.get(i).size();
            List<Permission> childList = permissions.get(i);
            for (int j = 0; j < childSize; j++) {
                perIds.add(childList.get(j).getId());
                perNames.add(childList.get(j).getPermissionName());
            }
        }

        List<Long> disPerIds = perIds.stream().distinct().collect(Collectors.toList());
        List<String> disPerNames = perNames.stream().distinct().collect(Collectors.toList());

        map.put("roleId", ids);
        map.put("permissionIds", disPerIds);
        map.put("permissionNames", disPerNames);

        return map == null ? Result.error(CodeMsg.ERROR) : Result.success(map);
    }

    @PostMapping("/batchInsert")
    @ApiOperation(value = "根据角色id批量更新权限记录(测试通过)", notes = "根据id批量插入权限记录")
    public Result<Object> batchInsert(@RequestParam List<Long> permissions, @RequestParam Long rid) {
        List<Long> dbPermissions = rolePermissionService.listPerissionIdByRid(rid);
        List<String> oldPermissions = dbPermissions.stream().map(a -> String.valueOf(a)).collect(Collectors.toList());
        List<String> newPermissions = permissions.stream().map(a -> String.valueOf(a)).collect(Collectors.toList());
        boolean equals = oldPermissions.stream().collect(Collectors.joining())
                                 .equals(newPermissions.stream().collect(Collectors.joining()));

        //新权限与旧权限相同,不操作数据库
        if (equals) {
            return Result.success(CodeMsg.SUCCESS);
        } else {
            //新建角色指定权限
            if (dbPermissions.size() == 0) {
                int batchInsert = rolePermissionService.batchInsert(permissions, rid);
                return batchInsert > 0 ? Result.success(CodeMsg.SUCCESS) : Result.error(CodeMsg.ERROR);
            } else {
                //已有角色修改权限
                List<Long> oldPerIds = dbPermissions.stream().filter(t -> !permissions.contains(t)).collect(Collectors.toList());
                List<Long> newPerIds = permissions.stream().filter(t -> !dbPermissions.contains(t)).collect(Collectors.toList());
                List<Long> common = dbPermissions.stream().filter(t -> permissions.contains(t)).collect(Collectors.toList());
                //已有角色权限全更新
                if (common.size() == 0) {
                    int batchDelete = rolePermissionService.batchDelete(dbPermissions, rid);
                    int batchInsert = rolePermissionService.batchInsert(permissions, rid);
                    return (batchDelete > 0 && batchInsert > 0) ? Result.success(CodeMsg.SUCCESS) : Result.error(CodeMsg.ERROR);
                } else {
                    //部分更新
                    int batchDelete = rolePermissionService.batchDelete(oldPerIds, rid);
                    int batchInsert = rolePermissionService.batchInsert(newPerIds, rid);
                    return (batchDelete > 0 && batchInsert > 0) ? Result.success(CodeMsg.SUCCESS) : Result.error(CodeMsg.ERROR);
                }
            }
        }
    }

    @GetMapping("/getAll")
    @ApiOperation(value = "获取权限列表，用于为新角色指定权限使用(测试通过)", notes = "获取权限列表")
    public Result listAll() {
        List<Permission> permissions = rolePermissionService.listAllPermission();
        return permissions == null ? Result.error(CodeMsg.ERROR) : Result.success(permissions);
    }

    @GetMapping("/getPermissions")
    @ApiOperation(value = "根据用户id获取对应权限集合(测试通过)", notes = "获取权限集合")
    public Result getPermissions(@RequestParam Long id) {
        Map<String, Object> map = new HashMap<>();
        List<Long> perIds = new ArrayList<>();  //权限id集合
        List<String> perNames = new ArrayList<>(); //权限Name集合
        List<Role> roles = roleService.listRolesByUid(id);
        List<Long> roleIds = roles.stream().map(Role::getId).collect(Collectors.toList());
        List<RoleVo> roleVos = rolePermissionService.listByRid(roleIds);
        List<List<Permission>> permissions = roleVos.stream().map(RoleVo::getPermissions).collect(Collectors.toList());
        int fatherSize = permissions.size();

        for (int i = 0; i < fatherSize; i++) {
            int childSize = permissions.get(i).size();
            List<Permission> childList = permissions.get(i);
            for (int j = 0; j < childSize; j++) {
                perIds.add(childList.get(j).getId());
                perNames.add(childList.get(j).getPermissionName());
            }
        }
        List<Long> disPerIds = perIds.stream().distinct().collect(Collectors.toList());
        List<String> disPerNames = perNames.stream().distinct().collect(Collectors.toList());

        map.put("userId", id);
        map.put("permissionIds", disPerIds);
        map.put("permissionNames", disPerNames);

        return map == null ? Result.error(CodeMsg.ERROR) : Result.success(map);
    }
}

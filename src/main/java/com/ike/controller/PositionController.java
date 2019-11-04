package com.ike.controller;

import com.ike.common.constans.CodeMsg;
import com.ike.common.result.Result;
import com.ike.pojo.Department;
import com.ike.pojo.Position;
import com.ike.service.PositionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "职位控制器", description = "职位信息模块(已添加权限配置,需要登陆[Kyre 123456]才能测试)")
@RestController
@RequestMapping("/json/position")
@RequiresAuthentication
@RequiresPermissions(value = "manage_dept:read")
public class PositionController {
    @Autowired
    private PositionService positionService;

    @GetMapping("/getDetail")
    @ApiOperation(value = "根据Id获取职位信息(测试通过)", notes = "根据Id获取职位信息")
    public Result<Object> detail(@RequestParam Long id){
        Position position = positionService.selectById(id);
        return position == null ? Result.error(CodeMsg.ERROR) : Result.success(position);
    }

    @PostMapping("/create")
    @ApiOperation(value = "新增一条职位记录,职位名称唯一(测试通过)", notes = "新增一条职位记录")
    public Result<Object> create(@RequestBody Position position){
        Position select = positionService.selectByPositionName(position.getPositionName());
        if (select != null) {
            return Result.error(new CodeMsg("职位名称已经存在"));
        }
        int insert = positionService.insert(position);
        return insert > 0 ? Result.success(CodeMsg.SUCCESS) : Result.error(CodeMsg.ERROR);
    }

    @PutMapping("/update")
    @ApiOperation(value = "更新一条职位记录(测试通过)", notes = "更新一条职位记录")
    public Result<Object> update(Position position) {
        int update = positionService.update(position);
        return update > 0 ? Result.success(CodeMsg.SUCCESS) : Result.error(CodeMsg.ERROR);
    }

    @DeleteMapping("/delete")
    @ApiOperation(value = "根据Id删除一条职位记录(测试通过)", notes = "根据Id删除一条职位记录")
    public Result<Object> delete(Long id) {
        int delete = positionService.deleteById(id);
        return delete > 0 ? Result.success(CodeMsg.SUCCESS) : Result.error(CodeMsg.ERROR);
    }

    @GetMapping("/getAll")
    @ApiOperation(value = "获取职位列表(测试通过)", notes = "获取职位列表")
    public Result listAll(){
        List<Position> list = positionService.selectAll();
        return list == null ? Result.error(CodeMsg.ERROR) : Result.success(list);
    }
}

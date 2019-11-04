package com.ike.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ike.common.constans.CodeMsg;
import com.ike.common.result.Result;
import com.ike.pojo.CustomerLevel;
import com.ike.service.CustomerLevelService;
import com.ike.service.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * ClassName CustomerLevelController
 * Description TODO
 *
 * @author LonelySeven
 * @version 1.0
 * @date 2019/10/9 9:26
 **/
@Api(description = "客户等级信息模块")
@RestController
@RequestMapping("/customerLevel")
public class CustomerLevelController {

    @Autowired
    private CustomerLevelService customerLevelService;
    @Autowired
    private CustomerService customerService;

    /**
     * Author: mokuanyuan
     * @apiNote:
     * @since:  2019/10/9 9:59
     */
    @ApiOperation(value = "获取客户等级列表", notes = "测试通过时间 2019年10月9日 15点01分")
    @GetMapping("/getAll")
    public Result listAll(){

        return Result.success(customerLevelService.selectAll());

    }


    @ApiOperation(value = "获取某个客户等级信息实体详情", notes = "测试通过时间 2019年10月9日 15点01分")
    @GetMapping("/getDetail")
    public Result detail(@RequestParam Integer id){

        CustomerLevel customerLevel = customerLevelService.selectById(id.longValue());

        return customerLevel != null ? Result.success(customerLevel) :
                Result.error(CodeMsg.CUSTOMER_LEVEL_FAILURE);

    }

    /**
     * Author: mokuanyuan
     * @param customerLevel
     * @return com.ike.common.result.Result
     * @apiNote: 新增客户等级实体
     * @since:  2019/10/9 10:16
     */
    @ApiOperation(value = "新增客户等级信息实体", notes = "测试通过时间 2019年10月9日 15点01分")
    @ApiImplicitParam(name = "customerLevel", value = "客户等级信息实体", required = true, dataType = "CustomerLevel")
    @PostMapping("/create")
    public Result create(@RequestBody CustomerLevel customerLevel){
        if (customerLevelService.selectByName(customerLevel.getLevelName()).size() > 0) {
            return Result.error(405, "该客户等级名称已存在!");
        }
        return customerLevelService.insert(customerLevel) > 0 ?
                Result.success(null) : Result.error(CodeMsg.CUSTOMER_LEVEL_FAILURE);
    }

    @ApiOperation(value = "修改客户等级信息实体", notes = "测试通过时间 2019年10月9日 15点01分")
    @ApiImplicitParam(name = "customerLevel", value = "客户等级信息实体", required = true, dataType = "CustomerLevel")
    @PutMapping("/update")
    public Result update(@RequestBody CustomerLevel customerLevel){
        if (customerLevelService.selectByName(customerLevel.getLevelName()).size() > 0) {
            return Result.error(405, "该客户等级名称已存在!");
        }
        return customerLevelService.update(customerLevel) > 0 ?
                Result.success(null) : Result.error(CodeMsg.CUSTOMER_LEVEL_FAILURE);

    }

    @ApiOperation(value = "删除客户等级信息实体", notes = "测试通过时间 2019年10月9日 15点01分")
    @DeleteMapping("/delete")
    public Result delete(@RequestParam Integer id){
        if (customerService.countOnElse("等级", id.longValue()) > 0) {
            return Result.error(405, "该等级有相应的客户依赖");
        }

        return customerLevelService.deleteById((long) id) > 0 ?
                Result.success(null) : Result.error(CodeMsg.CUSTOMER_LEVEL_FAILURE);

    }





}

package com.ike.controller;

import com.ike.common.constans.CodeMsg;
import com.ike.common.result.Result;
import com.ike.pojo.CustomerStage;
import com.ike.service.CustomerService;
import com.ike.service.CustomerStageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * ClassName CustomStageController
 * Description TODO
 *
 * @author LonelySeven
 * @version 1.0
 * @date 2019/10/9 17:17
 **/
@Api(description = "客户阶段模块")
@RestController
@RequestMapping("/CustomStage")
public class CustomerStageController {

    @Autowired
    private CustomerStageService customerStageService;
    @Autowired
    private CustomerService customerService;

    /**
     * Author: mokuanyuan
     * @apiNote:
     * @since:  2019/10/9 9:59
     */
    @ApiOperation(value = "获取客户阶段列表", notes = "已测试通过 2019年10月9日 17点19分")
    @GetMapping("/getAll")
    public Result listAll(){

        return Result.success(customerStageService.selectAll());

    }


    @ApiOperation(value = "获取某个客户阶段信息实体详情", notes = "已测试通过 2019年10月9日 17点19分")
    @GetMapping("/getDetail")
    public Result detail(@RequestParam Integer id){

        CustomerStage customerStage = customerStageService.selectById(id.longValue());

        return customerStage != null ? Result.success(customerStage) :
                Result.error(CodeMsg.CUSTOMER_LEVEL_FAILURE);

    }

    /**
     * Author: mokuanyuan
     * @param customerStage
     * @return com.ike.common.result.Result
     * @apiNote: 新增客户阶段实体
     * @since:  2019/10/9 10:16
     */
    @ApiOperation(value = "新增客户阶段信息实体", notes = "已测试通过 2019年10月9日 17点19分")
    @ApiImplicitParam(name = "customerStage", value = "客户阶段信息实体", required = true, dataType = "CustomerStage")
    @PostMapping("/create")
    public Result create(@RequestBody CustomerStage customerStage){
        if (customerStageService.selectByName(customerStage.getStageName()).size() > 0) {
            return Result.error(405, "该客户阶段名称已存在!");
        }
        return customerStageService.insert(customerStage) > 0 ?
                Result.success(null) : Result.error(CodeMsg.CUSTOMER_LEVEL_FAILURE);

    }

    @ApiOperation(value = "修改客户阶段信息实体", notes = "已测试通过 2019年10月9日 17点19分")
    @ApiImplicitParam(name = "customerStage", value = "客户阶段信息实体", required = true, dataType = "CustomerStage")
    @PutMapping("/update")
    public Result update(@RequestBody CustomerStage customerStage){
        if (customerStageService.selectByName(customerStage.getStageName()).size() > 0) {
            return Result.error(405, "该客户阶段名称已存在!");
        }
        return customerStageService.update(customerStage) > 0 ?
                Result.success(null) : Result.error(CodeMsg.CUSTOMER_LEVEL_FAILURE);

    }

    @ApiOperation(value = "删除客户阶段信息实体", notes = "已测试通过 2019年10月9日 17点19分")
    @DeleteMapping("/delete")
    public Result delete(@RequestParam Integer id){
        if (customerService.countOnElse("阶段", id.longValue()) > 0) {
            return Result.error(405, "该阶段有相应的客户依赖");
        }

        return customerStageService.deleteById((long) id) > 0 ?
                Result.success(null) : Result.error(CodeMsg.CUSTOMER_LEVEL_FAILURE);

    }



}

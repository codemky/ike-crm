package com.ike.controller;

import com.ike.common.constans.CodeMsg;
import com.ike.common.result.Result;
import com.ike.pojo.CustomerOrigin;
import com.ike.service.CustomerOriginService;
import com.ike.service.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * ClassName CustomerOriginController
 * Description TODO
 *
 * @author LonelySeven
 * @version 1.0
 * @date 2019/10/9 16:15
 **/
@Api(description = "客户来源模块")
@RestController
@RequestMapping("/customerOrigin")
public class CustomerOriginController {


    @Autowired
    private CustomerOriginService customerOriginService;
    @Autowired
    private CustomerService customerService;

    /**
     * Author: mokuanyuan
     * @apiNote:
     * @since:  2019/10/9 9:59
     */
    @ApiOperation(value = "获取客户来源列表", notes = "已测试通过")
    @GetMapping("/getAll")
    public Result listAll(){

        return Result.success(customerOriginService.selectAll());

    }


    @ApiOperation(value = "获取某个客户来源信息实体详情", notes = "已测试通过")
    @GetMapping("/getDetail")
    public Result detail(@RequestParam Integer id){

        CustomerOrigin customerOrigin = customerOriginService.selectById(id.longValue());

        return customerOrigin != null ? Result.success(customerOrigin) :
                Result.error(CodeMsg.CUSTOMER_LEVEL_FAILURE);

    }

    /**
     * Author: mokuanyuan
     * @param customerOrigin
     * @return com.ike.common.result.Result
     * @apiNote: 新增客户来源实体
     * @since:  2019/10/9 10:16
     */
    @ApiOperation(value = "新增客户来源信息实体", notes = "已测试通过")
    @ApiImplicitParam(name = "customerOrigin", value = "客户来源信息实体", required = true, dataType = "CustomerOrigin")
    @PostMapping("/create")
    public Result create(@RequestBody CustomerOrigin customerOrigin){
        if (customerOriginService.selectByName(customerOrigin.getOriginName()).size() > 0) {
            return Result.error(405, "该客户来源名称已存在!");
        }
        return customerOriginService.insert(customerOrigin) > 0 ?
                Result.success(null) : Result.error(CodeMsg.CUSTOMER_LEVEL_FAILURE);

    }

    @ApiOperation(value = "修改客户来源信息实体", notes = "已测试通过")
    @ApiImplicitParam(name = "customerOrigin", value = "客户来源信息实体", required = true, dataType = "CustomerOrigin")
    @PutMapping("/update")
    public Result update(@RequestBody CustomerOrigin customerOrigin){
        if (customerOriginService.selectByName(customerOrigin.getOriginName()).size() > 0) {
            return Result.error(405, "该客户来源名称已存在!");
        }
        return customerOriginService.update(customerOrigin) > 0 ?
                Result.success(null) : Result.error(CodeMsg.CUSTOMER_LEVEL_FAILURE);

    }

    @ApiOperation(value = "删除客户来源信息实体", notes = "已测试通过")
    @DeleteMapping("/delete")
    public Result delete(@RequestParam Integer id){
        if (customerService.countOnElse("来源", id.longValue()) > 0) {
            return Result.error(405, "该来源有相应的客户依赖");
        }

        return customerOriginService.deleteById((long) id) > 0 ?
                Result.success(null) : Result.error(CodeMsg.CUSTOMER_LEVEL_FAILURE);

    }

}

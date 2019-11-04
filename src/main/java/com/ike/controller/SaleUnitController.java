package com.ike.controller;

import com.ike.common.result.Result;
import com.ike.pojo.SaleUnit;
import com.ike.service.SaleUnitService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author wuchuxin
 * @Date 2019/10/9 9:58
 * @Version 1.0
 */
@RestController
@RequestMapping("/saleUnit/")
@Api(description = "产品销售单位模块")
public class SaleUnitController {

    @Autowired
    private SaleUnitService saleUnitService;

    @ApiOperation(value = "列出所有产品单位")
    @GetMapping("listAll")
    public Result listAll(){
        return Result.success(saleUnitService.listAll());
    }

    @ApiOperation(value = "添加新的产品单位")
    @PostMapping("create")
    public Result createSaleUnit(@RequestBody(required = false) SaleUnit saleUnit){
        return Result.success(saleUnitService.save(saleUnit));
    }

    @ApiOperation(value = "修改产品单位")
    @PutMapping("update")
    public Result updateSaleUnit(@RequestBody(required = false) SaleUnit saleUnit){
        return Result.success(saleUnitService.update(saleUnit));
    }

    @ApiOperation(value = "删除产品单位")
    @DeleteMapping("delete")
    public Result deleteSaleUnit(@RequestParam(value = "id") Long id) {
        return Result.success(saleUnitService.delete(id));
    }



}

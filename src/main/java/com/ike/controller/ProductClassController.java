package com.ike.controller;

import com.ike.common.result.Result;
import com.ike.pojo.ProductClass;
import com.ike.service.ProductClassService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author wuchuxin
 * @Date 2019/10/9 10:30
 * @Version 1.0
 */
@RestController
@RequestMapping("/productClass/")
@Api(description = "产品类别模块")
public class ProductClassController {

    @Autowired
    private ProductClassService productClassService;

    @ApiOperation(value = "获取所有类别")
    @GetMapping("listAll")
    public Result listAll() {
        return Result.success(productClassService.listAll());
    }

    @ApiOperation(value = "根据上一类别id查找产品类别")
    @GetMapping("listByPreId")
    public Result listByPreId(@RequestParam(value = "id") Long id) {
        return Result.success(productClassService.listByPreId(id));
    }

    @ApiOperation(value = "根据类别id查找产品类别名")
    @GetMapping("listById")
    public Result listById(@RequestParam(value = "id") Long id) {
        return Result.success(productClassService.listById(id));
    }

    @ApiOperation(value = "添加新的产品类别")
    @PostMapping("create")
    public Result create(@RequestBody(required = false)ProductClass productClass){
        //添加完需要修改所属同类的层数
        productClass.getPreviousClassId();
        return Result.success(productClassService.save(productClass));
    }

    @ApiOperation(value = "删除产品类别")
    @DeleteMapping("delete")
    public Result delete(@RequestParam(value = "id") Long id) {
        return Result.success(productClassService.delete(id));
    }

    @ApiOperation("修改产品类别")
    @PutMapping("update")
    public Result update(@RequestBody(required = false) ProductClass productClass){
        return Result.success(productClassService.update(productClass));
    }


}

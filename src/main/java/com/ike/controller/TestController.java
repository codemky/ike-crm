package com.ike.controller;


import com.ike.common.result.Result;
import com.ike.service.TestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author ike
 * @since 2019-09-30
 */
@RestController
@RequestMapping("/test")
@Api("测试controller")
public class TestController {

    @Autowired
    TestService testService;

    @GetMapping("/test")
    @ApiOperation("getList")
    public Result getList() {
        return Result.success(testService.getTestList());
    }
}


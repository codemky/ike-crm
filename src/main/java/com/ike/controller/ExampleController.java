package com.ike.controller;

import com.ike.common.result.Result;
import com.ike.pojo.Customer;
import com.ike.pojo.Position;
import com.ike.pojo.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("example")
@Api(value = "example测试")
public class ExampleController {

    @Autowired
    private ValueOperations operations;

    @ApiOperation("hello 测试接口")
    @GetMapping("hello")
    public Result<String> example() {
        return Result.success("Hello");
    }

    @ApiOperation("redis 测试接口")
    @GetMapping("redis")
    public Result getRedisData() {
        Position position = new Position();
        position.setId(1L);
        position.setPositionName("Java 后端开发");
        operations.set("position1", position);
        return Result.success(operations.get("position1"));
    }

    @ApiOperation("user信息 测试接口")
    @GetMapping("user")
    public Result getUserData(User user) {
        return Result.success(user);
    }


    @ApiOperation("获取GET请求参数并包装")
    @GetMapping("param")
    public Result getParam(@RequestBody Customer customer){

        System.out.println(customer.toString());

        return Result.success(null);

    }


}

package com.ike.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ike.common.constans.CodeMsg;
import com.ike.common.result.Result;
import com.ike.pojo.OrderProduct;
import com.ike.pojo.vo.OrderProductVo;
import com.ike.service.OrderProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Date: 2019-10-12 2:08
 * @Description:(描述)
 */
@RestController
@RequestMapping("/json/orderProduct")
@Api(description = "订单产品模块")
public class OrderProductController {

    @Autowired
    OrderProductService orderProductService;

    @ApiOperation(value = "根据订单的id来查询他的订单产品信息", notes = "已测")
    @GetMapping("listByOrderBaseId")
    public Result listByOrderBaseId(@ApiParam(name = "order_base_id", value = "订单ID", required = true)
                                    @RequestParam(value = "order_base_id", required = true) Long order_base_id,
                                    @RequestParam(value = "pageNum", defaultValue = "1", required = false) Integer pageNum,
                                    @RequestParam(value = "pageSize", defaultValue = "15", required = false) Integer pageSize) {
        List<OrderProductVo> orderProducts = orderProductService.selectAllByOrderBaseId(order_base_id);
        PageHelper.startPage(pageNum, pageSize);
        PageInfo<OrderProductVo> pageInfo = new PageInfo<>(orderProducts);
        return Result.success(pageInfo);
    }

    @ApiOperation(value = "创建一个新的订单和产品的关系", notes = "已测")
    @PostMapping("create")
    public Result create(OrderProduct orderProduct) {
        int insert = orderProductService.insert(orderProduct);
        return Result.success(orderProduct.getId());
    }

    @ApiOperation(value = "获取当前订单的金额",notes = "已测")
    @GetMapping("detailMoney")
    public Result detailMoney(@ApiParam(name = "order_base_id", value = "订单ID", required = true)
                              @RequestParam(value = "order_base_id", required = true) Long order_base_id) {
        Double money = orderProductService.selectMoney(order_base_id);
        return Result.success(money);
    }

    @ApiOperation(value = "根据订单Id查询一个订单下的所有产品列表", notes = "未测试")
    @GetMapping("/listByOrderProductId")
    public Result listByOrderProductId(@ApiParam(name = "id", value = "订单产品ID", required = true)
                                       @RequestParam(value = "id", required = true) Long id,
                                       @RequestParam(value = "pageNum", defaultValue = "1", required = false) Integer pageNum,
                                       @RequestParam(value = "pageSize", defaultValue = "15", required = false) Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<OrderProductVo> orderProductVo = orderProductService.showOrderProductList(id);
        PageInfo<OrderProductVo> info = new PageInfo<>(orderProductVo);
        return orderProductVo == null ?
                Result.error(CodeMsg.ERROR) : Result.success(info);
    }
}

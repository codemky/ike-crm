package com.ike.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ike.common.constans.CodeMsg;
import com.ike.common.result.Result;
import com.ike.pojo.ReturnPlan;
import com.ike.pojo.User;
import com.ike.pojo.vo.ReturnPlanVo;
import com.ike.service.ReturnPlanDetailService;
import com.ike.service.ReturnPlanService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Description TODO
 * @Date 2019/10/12 0:05
 */
@RestController
@RequestMapping("/json/returnPlan/")
@Api(description = "回款计划模块")
public class ReturnPlanController {

    @Autowired
    ReturnPlanService returnPlanService;

    @Autowired
    ReturnPlanDetailService returnPlanDetailService;

    @ApiOperation(value = "新增回款计划", notes = "测试通过")
    @PostMapping("create")
    public Result create(User user,
                         @RequestBody ReturnPlan returnPlan) {
        createAction(returnPlan, user);
        return returnPlanService.insert(returnPlan) > 0 ?
                Result.success(null) : Result.error(CodeMsg.CUSTOMER_LEVEL_FAILURE);
    }

    @ApiOperation(value = "查询所有的回款计划")
    @GetMapping("listAll")
    public Result listAll(@RequestParam(value = "pageNum", defaultValue = "1", required = false) Integer pageNum,
                          @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<ReturnPlan> r_list = returnPlanService.selectAll();
        PageInfo<ReturnPlan> pageinfo = new PageInfo<ReturnPlan>(r_list, pageSize);
        return Result.success(pageinfo);
    }

    @ApiOperation(value = "根据ID查询回款计划")
    @GetMapping("detail")
    public Result detail(@ApiParam(name = "id", value = "回款计划ID", required = true)
                         @RequestParam(value = "id", required = true) Long id) {
        ReturnPlan returnPlan = returnPlanService.selectById(id);
        return Result.success(returnPlan);
    }

    @ApiOperation("修改回款计划")
    @PutMapping("update")
    public Result update(User user,
                         @RequestBody(required = false) ReturnPlan returnPlan) {
        updateAction(returnPlan, user);
        return Result.success(returnPlanService.update(returnPlan));
    }

    @ApiOperation(value = "根据ID删除回款计划")
    @DeleteMapping("delete")
    public Result delete(@ApiParam(name = "id", value = "回款计划ID", required = true)
                         @RequestParam(value = "id", required = true) Long id) {
        int falg = returnPlanService.deleteByKeyId(id);
        //返回状态为1 删除成功，返回状态为0 删除失败
        return falg > 0
                ? Result.success(1) : Result.error(CodeMsg.PLAN_DELETE_ERROR);
    }

    @ApiOperation(value = "根据订单ID查询回款计划和回款明细")
    @GetMapping("detailByOrderId")
    public Result detailByOrderId(@ApiParam(name = "order_base_id", value = "订单ID", required = true)
                                  @RequestParam(value = "order_base_id", required = true) Long order_base_id) {
        ReturnPlan returnPlan = returnPlanService.findByOrderId(order_base_id);
        List<ReturnPlanVo> returnPlanDetails = returnPlanDetailService.selectListByPlanId(returnPlan.getId());
        return Result.success(returnPlanDetails);
    }


    private static void updateAction(ReturnPlan returnPlan, User user) {
        returnPlan.setModifyUserId(user.getId());
        returnPlan.setModifyTime(LocalDateTime.now());
    }

    private static void createAction(ReturnPlan returnPlan, User user) {
        returnPlan.setCreateUserId(user.getId());
        returnPlan.setCreateTime(LocalDateTime.now());
        returnPlan.setModifyUserId(user.getId());
        returnPlan.setModifyTime(LocalDateTime.now());
    }

    @ApiOperation(value = "修改计划表和订单表之间的关联", notes = "已测")
    @PutMapping("/updateReturnPlanId")
    public Result updateReturnPlanId(@ApiParam(name = "old_plan_id", value = "要取消关联的计划ID", required = true)
                                     @RequestParam(value = "old_plan_id", required = true) Long old_plan_id,
                                     @ApiParam(name = "new_plan_id", value = "要关联的新的计划ID", required = true)
                                     @RequestParam(value = "new_plan_id", required = true) Long new_plan_id) {
        int falg = returnPlanService.updateReturnPlanId(old_plan_id, new_plan_id);
        return falg > 0
                ? Result.success(falg) : Result.error(CodeMsg.PLAN_DELETE_ERROR);
    }
}

package com.ike.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ike.common.constans.CodeMsg;
import com.ike.common.result.Result;
import com.ike.pojo.ReturnPlanDetail;
import com.ike.pojo.User;
import com.ike.pojo.vo.ReturnPlanVo;
import com.ike.service.ReturnPlanDetailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Description TODO
 * @Date 2019/10/12 1:11
 */
@RestController
@RequestMapping("/returnPlanDetail/")
@Api(description = "回款计划明细模块")
public class ReturnPlanDetailController {
    @Autowired
    ReturnPlanDetailService returnPlanDetailService;

    @ApiOperation(value = "创建回款计划明细信息", notes = "测试通过")
    @PostMapping("create")
    public Result create(User user,
                         @RequestBody ReturnPlanDetail returnPlanDetail){
        createAction(returnPlanDetail,user);
        return returnPlanDetailService.insert(returnPlanDetail) > 0 ?
                Result.success(null) : Result.error(CodeMsg.CUSTOMER_LEVEL_FAILURE);
    }

    @ApiOperation(value = "根据回款计划ID查询回款计划明细及关联的回款计划基本信息")
    @GetMapping("listAllByPlanId")
    public Result listAllByPlanId(@ApiParam(name = "return_plan_id", value = "回款计划ID", required = true)
                                  @RequestParam(value = "return_plan_id", required = true) Long return_plan_id,
                                  @RequestParam(value = "pageNum", defaultValue = "1", required = false) Integer pageNum,
                                  @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<ReturnPlanVo> r_list = returnPlanDetailService.selectListByPlanId(return_plan_id);
        PageInfo<ReturnPlanVo> pageinfo = new PageInfo<ReturnPlanVo>(r_list, pageSize);
        return Result.success(pageinfo);
    }

    @ApiOperation(value = "查询所有回款计划明细及关联的回款计划基本信息")
    @GetMapping("listAll")
    public Result listAll(@RequestParam(value = "pageNum", defaultValue = "1", required = false) Integer pageNum,
                          @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<ReturnPlanVo> r_list = returnPlanDetailService.listAll();
        PageInfo<ReturnPlanVo> pageinfo = new PageInfo<ReturnPlanVo>(r_list, pageSize);
        return Result.success(pageinfo);
    }

    @ApiOperation(value = "根据回款计划ID查询其关联的回款计划明细个数")
    @GetMapping("countId")
    public Result countId(@ApiParam(name = "return_plan_id", value = "回款计划ID", required = true)
                          @RequestParam(value = "return_plan_id",required = true) Long return_plan_id){
        int falg = returnPlanDetailService.countId(return_plan_id);
        return Result.success(falg);
    }

    @ApiOperation(value = "根据回款计划ID和回款序号查询回款计划明细")
    @GetMapping("detailByPlanIdAndDetailId")
    public Result detailByPlanIdAndDetailId(@ApiParam(name = "return_plan_id", value = "回款计划ID", required = true)
                                            @RequestParam(value = "return_plan_id",required = true) Long return_plan_id,
                                            @ApiParam(name = "return_number", value = "回款序号", required = true)
                                            @RequestParam(value = "return_number",required = true) Integer return_number){
        List<ReturnPlanDetail> returnPlanDetail = returnPlanDetailService.selectByReturnPlanIdAndReturnNumber(return_plan_id, return_number);
        return Result.success(returnPlanDetail);
    }

    @ApiOperation("修改回款计划明细")
    @PutMapping("update")
    public Result update(User user,
                         @RequestBody(required = false) ReturnPlanDetail returnPlanDetail){
        updateAction(returnPlanDetail,user);
        return Result.success(returnPlanDetailService.update(returnPlanDetail));
    }

    @ApiOperation(value = "根据ID删除回款计划明细")
    @DeleteMapping("delete")
    public Result delete(@ApiParam(name = "id", value = "回款计划明细ID", required = true)
                         @RequestParam(value = "id",required = true) Long id){
        int falg = returnPlanDetailService.deleteByKeyId(id);
        //返回状态为1 删除成功，返回状态为0 删除失败
        return falg == 0
                ?  Result.success(1) : Result.error(CodeMsg.PLANDETAIL_DELETE_ERROR);
    }

    private static void updateAction(ReturnPlanDetail returnPlanDetail, User user) {
        returnPlanDetail.setModifyUserId(user.getId());
        returnPlanDetail.setModifyTime(LocalDateTime.now());
    }

    private static void createAction(ReturnPlanDetail returnPlanDetail, User user) {
        returnPlanDetail.setCreateUserId(user.getId());
        returnPlanDetail.setCreateTime(LocalDateTime.now());
        returnPlanDetail.setModifyUserId(user.getId());
        returnPlanDetail.setModifyTime(LocalDateTime.now());
    }
}

package com.ike.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ike.common.constans.CodeMsg;
import com.ike.common.result.Result;
import com.ike.common.util.ExcelUtil;
import com.ike.pojo.*;
import com.ike.pojo.vo.*;
import com.ike.service.CustomerService;
import com.ike.service.FollowPlanService;
import com.ike.service.FollowService;
import com.ike.service.RelationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * ClassName FollowPlanController
 * Description TODO
 *
 * @author mokuanyuan
 * @version 1.0
 * @date 2019/10/15 20:07
 **/
@Api(description = "客户跟进计划模块")
@RestController
@RequestMapping("/followPlan")
public class FollowPlanController {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private FollowPlanService followPlanService;
    @Autowired
    private RelationService relationService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private FollowService followService;

    @ApiOperation(value = "根据多种条件搜索客户跟进计划记录", notes = "")
    @GetMapping("/listByCriteria")
    public Result listByCriteria(User user,
                                 @RequestParam(value = "employeeIds", required = false) String employeeIds,
                                 @RequestParam(value = "minDate", required = false) String minDate,
                                 @RequestParam(value = "maxDate", required = false) String maxDate,
                                 @RequestParam(value = "isComplete", required = false) String isComplete,
                                 @RequestParam(value = "notComplete", required = false) String notComplete,
                                 @RequestParam(value = "notExecute", required = false) String notExecute,
                                 @RequestParam(value = "customerName", required = false) String customerName,
                                 @RequestParam(value = "relationName", required = false) String relationName,
                                 @RequestParam(value = "relationPhone", required = false) String relationPhone,
                                 @RequestParam(value = "customerId", required = false) Long customerId,
                                 @RequestParam(value = "relationId", required = false) Long relationId,
                                 @RequestParam(value = "isMyself", required = false) String isMyself,

                                 @RequestParam(value = "isToday", required = false) String isToday,
                                 @RequestParam(value = "isTomorrow", required = false) String isTomorrow,
                                 @RequestParam(value = "isAfterTomorrow", required = false) String isAfterTomorrow,
                                 @RequestParam(value = "isWeek", required = false) String isWeek,
                                 @RequestParam(value = "sortName", required = false) String sortName,
                                 @RequestParam(value = "sortType", required = false) String sortType,

                                 @RequestParam(value = "pageNum", defaultValue = "1", required = false) Integer pageNum,
                                 @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize) {

        FollowPlanSearchVo criteria = FollowPlanSearchVo.getProperties(request, new FollowPlanSearchVo());
        if (criteria.getIsMyself() != null) {
            criteria.setEmployeeIds(Collections.singletonList(user.getId()));
        }

        IPage<FollowPlanVo> voPage = followPlanService.selectByCriteria(new Page<>(pageNum, pageSize), criteria);

        return Result.success(voPage);
    }


    @ApiOperation("添加跟进计划记录")
    @PostMapping("create")
    public Result create(User user, @RequestBody FollowPlan followPlan) {

        followPlan.setEmployeeId(user.getId());
        followPlan.setCreateDate(LocalDateTime.now());

        return followPlanService.create(followPlan) > 0 ?
                Result.success(null) : Result.error(CodeMsg.CUSTOMER_FOLLOW_PLAN_FAILURE);

    }

    @ApiOperation("更新跟进计划记录")
    @PutMapping("update")
    public Result update(User user, @RequestBody FollowPlan followPlan) {
        followPlan.setEmployeeId(user.getId());

        return followPlanService.update(followPlan) > 0 ?
                Result.success(null) : Result.error(CodeMsg.CUSTOMER_FOLLOW_PLAN_FAILURE);

    }

    @ApiOperation("完成跟进计划并创建相应的跟进记录")
    @PutMapping("complete")
    public Result complete(User user, @RequestBody FollowPlanCompleteVo completeVo) {

        Follow follow = new Follow();
        FollowPlan followPlan = new FollowPlan();
        BeanUtils.copyProperties(completeVo, follow);
        BeanUtils.copyProperties(completeVo, followPlan);
        followPlan.setIscomplete((byte) 0);

        Relation relation = relationService.selectById(completeVo.getRelationId());
        if (relation == null) {
            return Result.error(405, "找不到客户联系人");
        }
        Customer customer = customerService.selectById(relation.getId());
        if (customer == null) {
            return Result.error(405, "找不到客户");
        }

        follow.setCustomerStatus(customer.getCustomerState());
        follow.setCustomerStageId(customer.getCustomerStageId());
        follow.setCreateTime(LocalDateTime.now());
        follow.setCreateUserId(user.getId());
        follow.setCustomerId(customer.getId());

        followPlan.setEmployeeId(user.getId());

        if (followService.create(follow) == 0) {
            return Result.error(405, "创建跟进记录失败");
        }

        followPlan.setFollowId(follow.getId());
        return followPlanService.update(followPlan) > 0 ?
                Result.success(null) : Result.error(CodeMsg.CUSTOMER_FOLLOW_PLAN_FAILURE);

    }

    @ApiOperation("更新计划为未完成")
    @PutMapping("fail")
    public Result fail(User user, @RequestBody FollowPlan plan) {

        User option = Optional.ofNullable(user).orElse(new User());
        if (option.getId() == null) {
            return Result.error(CodeMsg.SESSION_ERROR);
        } else {
            plan.setEmployeeId(option.getId());
        }
        plan.setIscomplete((byte) 1);

        return followPlanService.update(plan) > 0 ?
                Result.success(null) : Result.error(405, "更新任务为未完成失败");

    }

    @ApiOperation("获取跟进计划记录详情")
    @GetMapping("detail")
    public Result detail(User user, @RequestParam("id") Long id) {

        User userOption = Optional.ofNullable(user).orElse(new User());
        if (userOption.getId() == null) {
            return Result.error(CodeMsg.SESSION_ERROR);
        }

        FollowPlan follow = followPlanService.selectDetail(id);
        if (follow == null) {
            return Result.error(500, "找不到该跟进计划记录");
        }

        return Result.success(follow);

    }

    @ApiOperation("删除跟进计划计划")
    @DeleteMapping("delete")
    public Result delete(User user, @RequestParam("id") Long id) {

        User userOption = Optional.ofNullable(user).orElse(new User());
        if (userOption.getId() == null) {
            return Result.error(CodeMsg.SESSION_ERROR);
        }

        return followPlanService.delete(id) > 0 ?
                Result.success(null) : Result.error(CodeMsg.CUSTOMER_FOLLOW_PLAN_FAILURE);

    }

    @ApiOperation("批量删除跟进计划计划")
    @DeleteMapping("deleteByIds")
    public Result deleteByIds(User user, @RequestBody List<Long> ids) {

        return followPlanService.deleteByIds(ids) > 0 ?
                Result.success(null) : Result.error(CodeMsg.CUSTOMER_FOLLOW_PLAN_FAILURE);

    }

    @ApiOperation(value = "把跟进记录的Excel表数据导入到数据库(测试通过)", notes = "批量导入")
    @PostMapping("/import")
    public Result importExcel(User user, MultipartFile file) throws Exception {
        if (null == file) {
            return Result.error(new CodeMsg("文件读取错误, 无效的文件!"));
        }

        List<Object> list = ExcelUtil.readExcel(file, new FollowPlan());

        if (null == list || list.size() == 0) {
            return Result.error(new CodeMsg("文件数据为空!"));
        }


        for (int i = 1; i <= list.size(); i++) {
            FollowPlan plan = (FollowPlan) list.get(i - 1);

            plan.setPlanDate(FollowController.randomDate("2018-01-01 00:00:00","2019-10-12 00:00:00"));
            plan.setCreateDate(plan.getPlanDate());

            if(FollowController.random(0L,1000L) %2 == 0){
                plan.setIscomplete((byte)0);
            }
            if(FollowController.random(0L,1000L) %3 == 0){
                plan.setIscomplete((byte)1);
            }

            followPlanService.create(plan);
        }



        return Result.success(CodeMsg.SUCCESS);
    }


}

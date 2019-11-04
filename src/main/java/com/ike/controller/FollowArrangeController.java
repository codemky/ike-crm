package com.ike.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ike.common.constans.CodeMsg;
import com.ike.common.result.Result;
import com.ike.pojo.*;
import com.ike.pojo.vo.*;
import com.ike.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

/**
 * ClassName FollowArrangeController
 * Description TODO
 *
 * @author mokuanyuan
 * @version 1.0
 * @date 2019/10/16 9:18
 **/
@Api(description = "客户跟进任务模块")
@RestController
@RequestMapping("/followArrange")
@Slf4j
public class FollowArrangeController {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private FollowArrangeService followArrangeService;
    @Autowired
    private RelationService relationService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private FollowService followService;
    @Autowired
    private MessageService messageService;
    @Autowired
    private UserService userService;

    @ApiOperation(value = "根据多种条件搜索客户跟进任务记录", notes = "")
    @GetMapping("/listByCriteria")
    public Result listByCriteria(User user,
                                 @RequestParam(value = "arrangeId", required = false) Long arrangeId,
                                 @RequestParam(value = "chargeId", required = false) Long chargeId,
                                 @RequestParam(value = "arrangeMyself", required = false) String arrangeMyself,
                                 @RequestParam(value = "chargeMyself", required = false) String chargeMyself,
                                 @RequestParam(value = "customerId", required = false) Long customerId,
                                 @RequestParam(value = "relationId", required = false) Long relationId,
                                 @RequestParam(value = "arrangeName", required = false) String arrangeName,
                                 @RequestParam(value = "chargeName", required = false) String chargeName,
                                 @RequestParam(value = "minDate", required = false) String minDate,
                                 @RequestParam(value = "maxDate", required = false) String maxDate,
                                 @RequestParam(value = "isComplete", required = false) String isComplete,
                                 @RequestParam(value = "notComplete", required = false) String notComplete,
                                 @RequestParam(value = "notExecute", required = false) String notExecute,
                                 @RequestParam(value = "idRead", required = false) String idRead,
                                 @RequestParam(value = "notRead", required = false) String notRead,
                                 @RequestParam(value = "customerName", required = false) String customerName,
                                 @RequestParam(value = "relationName", required = false) String relationName,
                                 @RequestParam(value = "relationPhone", required = false) String relationPhone,
                                 @RequestParam(value = "sortName", required = false) String sortName,
                                 @RequestParam(value = "sortType", required = false) String sortType,

                                 @RequestParam(value = "pageNum", defaultValue = "1", required = false) Integer pageNum,
                                 @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize) {

        FollowArrangeSearchVo criteria = FollowArrangeSearchVo.getProperties(request, new FollowArrangeSearchVo());
        if (criteria.getArrangeMyself() != null) {
            criteria.setArrangeId(user.getId());
        }
        if (criteria.getChargeMyself() != null) {
            criteria.setChargeId(user.getId());
        }

        IPage<FollowArrangeVo> voPage = followArrangeService.selectByCriteria(new Page<>(pageNum, pageSize), criteria);

        return Result.success(voPage);
    }


    @ApiOperation("添加跟进任务记录")
    @PostMapping("create")
    public Result create(User user, @RequestBody FollowArrange followArrange) {

        Long arrangeId = followArrange.getArrangeId();
        if (null == arrangeId) {
            arrangeId = user.getId();
        }

        Long chargeId = followArrange.getChargeId();
        if (null == chargeId) {
            chargeId = user.getId();
        }
        if (arrangeId == null || chargeId == null) {
            return Result.error(405, "安排人或负责人的id为空");
        }
        followArrange.setArrangeId(arrangeId);
        followArrange.setChargeId(chargeId);
        if (null == followArrange.getArrangeContent()) {
            return Result.error(new CodeMsg("任务内容不能为空"));
        }
        followArrange.setCreateTime(LocalDateTime.now());
        followArrange.setCreateUserId(user.getId());

        UserVo arrangeUser = userService.selectById(arrangeId);
        UserVo chargeUser = userService.selectById(chargeId);
        if (arrangeUser == null || chargeUser == null) {
            return Result.error(405, "安排人或负责人的信息为空");
        }
        String arrangeName = arrangeUser.getName();
//        String chargeName = chargeUser.getUsername();
        if (arrangeName.isEmpty()) {
            return Result.error(405, "安排人的信息为空");
        }
        String title = arrangeName + "安排了一个任务给你";
        String arrangeContent = followArrange.getArrangeContent();
//        String contentForArrange = "你安排了一个任务给" + chargeName;

        Message message = new Message();
        message.setFromId(arrangeId);
        message.setToId(chargeId);
        message.setReaded((byte) 0);
        message.setMsgTitle(title);
        message.setMsgContent(arrangeContent);
        message.setSendTime(LocalDateTime.now());

        messageService.sendMessage(message);
        createAction(followArrange, Optional.ofNullable(user).orElseGet(User::new));
        return followArrangeService.create(followArrange) > 0 ?
                Result.success(null) : Result.error(CodeMsg.CUSTOMER_FOLLOW_ARRANGE_FAILURE);

    }

    @ApiOperation("更新跟进任务记录")
    @PutMapping("update")
    public Result update(User user, @RequestBody FollowArrange followArrange) {

        updateAction(followArrange, Optional.ofNullable(user).orElseGet(User::new));
        return followArrangeService.update(followArrange) > 0 ?
                Result.success(null) : Result.error(CodeMsg.CUSTOMER_FOLLOW_ARRANGE_FAILURE);

    }

    @ApiOperation("完成跟进任务并创建相应的跟进记录")
    @PutMapping("complete")
    public Result complete(User user, @RequestBody FollowArrangeCompleteVo arrangeCompleteVo) {

        Follow follow = new Follow();
        FollowArrange followArrange = new FollowArrange();
        BeanUtils.copyProperties(arrangeCompleteVo, follow);
        BeanUtils.copyProperties(arrangeCompleteVo, followArrange);

        if (!arrangeCompleteVo.getChargeId().equals(user.getId())) {
            return Result.error(new CodeMsg("该任务的执行人不是你"));
        }

        Relation relation = relationService.selectById(arrangeCompleteVo.getRelationId());
        if (relation == null) {
            return Result.error(405, "找不到客户联系人");
        }
        Customer customer = customerService.selectById(relation.getId());
        if (customer == null) {
            return Result.error(405, "找不到客户");
        }

        follow.setCustomerStatus(customer.getCustomerState());
        follow.setCustomerStageId(customer.getCustomerStageId());
        follow.setCustomerId(customer.getId());
        follow.setEmployeeId(user.getId());

        followArrange.setIscomplete((byte) 0);
        followArrange.setExecuteTime(LocalDateTime.now());
        updateAction(followArrange, Optional.ofNullable(user).orElseGet(User::new));

        if (followService.create(follow) == 0) {
            return Result.error(405, "创建跟进记录失败");
        }

        Long arrangeId = followArrange.getArrangeId();
        Long chargeId = followArrange.getChargeId();
        if (arrangeId == null || chargeId == null) {
            return Result.error(405, "安排人或负责人的id为空");
        }

        UserVo arrangeUser = userService.selectById(arrangeId);
        UserVo chargeUser = userService.selectById(chargeId);
        if (arrangeUser == null || chargeUser == null) {
            return Result.error(405, "安排人或负责人的信息为空");
        }
//        String arrangeName = arrangeUser.getName();
        String chargeName = chargeUser.getName();
        if (chargeName.isEmpty()) {
            return Result.error(405, "安排人的信息为空");
        }
        String title = chargeName + "完成了你安排的一个任务";
        String arrangeContent = chargeName + "完成任务时间为:" +
                LocalDateTime.now().toString() + "  详情请看任务详情和跟进详情";
//        String contentForArrange = "你安排了一个任务给" + chargeName;

        Message message = new Message();
        message.setFromId(chargeId);
        message.setToId(arrangeId);
        message.setReaded((byte) 0);
        message.setMsgTitle(title);
        message.setMsgContent(arrangeContent);
        message.setSendTime(LocalDateTime.now());

        messageService.sendMessage(message);

        if (followArrange.getChargeId().equals(user.getId())) {
            //设置为已读
            followArrange.setIsread((byte) 0);
        }
        followArrange.setFollowId(follow.getId());
        return followArrangeService.update(followArrange) > 0 ?
                Result.success(null) : Result.error(CodeMsg.CUSTOMER_FOLLOW_ARRANGE_FAILURE);

    }

    @ApiOperation("更新计划为未完成")
    @PutMapping("fail")
    public Result fail(User user, @RequestBody FollowArrange arrange) {

        if (!arrange.getChargeId().equals(user.getId())) {
            return Result.error(new CodeMsg("该任务的执行人不是你"));
        }

        User option = Optional.ofNullable(user).orElse(new User());
        if (option.getId() == null) {
            return Result.error(CodeMsg.SESSION_ERROR);
        }


        if (arrange.getChargeId().equals(user.getId())) {
            //设置为已读
            arrange.setIsread((byte) 0);
        }
        arrange.setExecuteTime(LocalDateTime.now());
        arrange.setIscomplete((byte) 1);
        updateAction(arrange, Optional.ofNullable(user).orElseGet(User::new));

        return followArrangeService.update(arrange) > 0 ?
                Result.success(null) : Result.error(405, "更新任务为未完成的操作失败");

    }

    @ApiOperation("获取跟进任务记录详情")
    @GetMapping("detail")
    public Result detail(User user, @RequestParam("id") Long id) {

        FollowArrangeVo arrangeVo = followArrangeService.selectDetail(id);
        if (arrangeVo == null) {
            return Result.error(500, "找不到该跟进任务记录");
        }
        FollowArrange arrange = followArrangeService.detail(arrangeVo.getId());
        if (arrange == null) {
            return Result.error(500, "找不到该跟进任务记录");
        }
        if (arrange.getChargeId().equals(user.getId())) {
            //设置为已读
            arrange.setIsread((byte) 0);
            followArrangeService.update(arrange);
        }

        return Result.success(arrangeVo);

    }

    @ApiOperation("删除跟进任务计划")
    @DeleteMapping("delete")
    public Result delete(User user, @RequestParam("id") Long id) {

        return followArrangeService.delete(id) > 0 ?
                Result.success(null) : Result.error(CodeMsg.CUSTOMER_FOLLOW_ARRANGE_FAILURE);
    }

    @ApiOperation("批量删除跟进任务")
    @DeleteMapping("deleteByIds")
    public Result deleteByIds(User user, @RequestBody List<Long> ids) {

        return followArrangeService.deleteByIds(ids) > 0 ?
                Result.success(null) : Result.error(CodeMsg.CUSTOMER_FOLLOW_PLAN_FAILURE);

    }

    private static void updateAction(FollowArrange followArrange, User user) {
        followArrange.setModifyUserId(user.getId());
        followArrange.setModifyTime(LocalDateTime.now());
    }

    private static void createAction(FollowArrange followArrange, User user) {
        followArrange.setModifyUserId(user.getId());
        followArrange.setModifyTime(LocalDateTime.now());
    }


}

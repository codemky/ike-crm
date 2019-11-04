package com.ike.controller;

import com.ike.common.constans.CodeMsg;
import com.ike.common.result.Result;
import com.ike.pojo.Customer;
import com.ike.pojo.Relation;
import com.ike.pojo.User;
import com.ike.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.amqp.rabbit.retry.NewMessageIdentifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * ClassName RelationController
 * Description TODO
 *
 * @author mokuanyuan
 * @version 1.0
 * @date 2019/10/11 21:45
 **/
@RestController
@RequestMapping("/relation")
@Api(description = "客户联系人模块")
public class RelationController {

    @Autowired
    private RelationService relationService;
    @Autowired
    private FollowService followService;
    @Autowired
    private FollowPlanService followPlanService;
    @Autowired
    private FollowArrangeService followArrangeService;
    @Autowired
    private ComplaintService complaintService;
    @Autowired
    private OrderBaseService orderBaseService;
    @Autowired
    private ReturnDetailService returnDetailService;
    @Autowired
    private RefundService refundService;
    @Autowired
    private CustomerService customerService;

    @ApiOperation(value = "修改客户的主联系人的手机号码", notes = "")
    @PutMapping("updatePhone")
    public Result updatePhone(@RequestParam("Rid") Long rid, @RequestParam("phone") String phone) {
        Relation relation = relationService.selectById(rid);
        if (relation == null) {
            return Result.error(CodeMsg.CUSTOMER_RELATION_FAILURE);
        }
        relation.setRelationPhone(phone);

        return relationService.update(relation) > 0 ?
                Result.success(null) : Result.error(CodeMsg.CUSTOMER_RELATION_FAILURE);
    }

    @ApiOperation(value = "修改客户的主联系人的座机号", notes = "")
    @PutMapping("updateNumber")
    public Result updateNumber(@RequestParam("Rid") Long rid, @RequestParam("number") String number) {
        Relation relation = relationService.selectById(rid);
        if (relation == null) {
            return Result.error(CodeMsg.CUSTOMER_RELATION_FAILURE);
        }
        relation.setLandlineNumber(number);

        return relationService.update(relation) > 0 ?
                Result.success(null) : Result.error(CodeMsg.CUSTOMER_RELATION_FAILURE);
    }

    /**
     * Author: mokuanyuan
     * @param cid 客户id
     * @return Result
     * @apiNote:
     * @since:  2019/10/11 21:55
     */
    @ApiOperation(value = "根据客户id获取客户联系人列表", notes = "已测试通过")
    @GetMapping("/listByCid")
    public Result listByCid(@RequestParam("Cid") Long cid){

        List<Relation> relations = relationService.selectByCustomId(cid);

        return relations != null ? Result.success(relations) :
                Result.error(CodeMsg.CUSTOMER_RELATION_FAILURE);

    }

    @ApiOperation(value = "获取某个客户联系人信息实体详情", notes = "已测试通过")
    @GetMapping("/getDetail")
    public Result detail(@RequestParam Integer id){

        Relation relation = relationService.selectById(id.longValue());

        return relation != null ? Result.success(relation) :
                Result.error(CodeMsg.CUSTOMER_RELATION_FAILURE);

    }

    /**
     * Author: mokuanyuan
     * @param relation
     * @return com.ike.common.result.Result
     * @apiNote: 新增客户联系人实体
     * @since:  2019/10/9 10:16
     */
    @ApiOperation(value = "新增客户联系人信息实体", notes = "已测试通过")
    @ApiImplicitParam(name = "relation", value = "客户联系人信息实体", required = true, dataType = "Relation")
    @PostMapping("/create")
    public Result create(@RequestBody Relation relation){
        if (relation.getCustomerId() == null) {
            return Result.error(405, "客户id为空");
        }
        if (relation.getRelationPrimary() == 0) {
            Relation primary = relationService.selectPrimaryByCid(relation.getCustomerId());
            if (null != primary && !primary.getId().equals(relation.getId())) {
                primary.setRelationPrimary((byte) 1);
                relation.setRelationPrimary((byte) 0);
                int update = relationService.update(primary);
                if (update == 0) {
                    return Result.error(405, "无法更新客户的主联系人");
                }
            }
        }

        return relationService.insert(relation) > 0 ?
                Result.success(null) : Result.error(CodeMsg.CUSTOMER_RELATION_FAILURE);

    }

    @ApiOperation(value = "修改客户联系人信息实体", notes = "已测试通过")
    @ApiImplicitParam(name = "relation", value = "客户联系人信息实体", required = true, dataType = "Relation")
    @PutMapping("/update")
    public Result update(@RequestBody Relation relation){
        if (relation.getCustomerId() == null) {
            return Result.error(405, "客户id为空");
        }
        if (relation.getRelationPrimary() == 0) {
            Relation primary = relationService.selectPrimaryByCid(relation.getCustomerId());
            if (null != primary && !primary.getId().equals(relation.getId())) {
                primary.setRelationPrimary((byte) 1);
                relation.setRelationPrimary((byte) 0);
                int update = relationService.update(primary);
                if (update == 0) {
                    return Result.error(405, "无法更新客户的主联系人");
                }
            }
        }

        return relationService.update(relation) > 0 ?
                Result.success(null) : Result.error(CodeMsg.CUSTOMER_RELATION_FAILURE);

    }

    @ApiOperation(value = "更新某个联系人为主联系人", notes = "已测试通过")
    @ApiImplicitParam(name = "relation", value = "客户联系人信息实体", required = true, dataType = "Relation")
    @PutMapping("/updatePrimary")
    public Result updatePrimary(@RequestParam Long id) {
        Relation relation = relationService.selectById(id);
        if (null == relation) {
            return Result.error(405, "找不到客户联系人");
        }

        Relation primary = relationService.selectPrimaryByCid(relation.getCustomerId());
        if (null != primary && !primary.getId().equals(relation.getId())) {
            primary.setRelationPrimary((byte) 1);
            relation.setRelationPrimary((byte) 0);
            int update = relationService.update(primary);
            if (update == 0) {
                return Result.error(405, "无法更新客户的主联系人");
            }
        }

        return relationService.update(relation) > 0 ?
                Result.success(null) : Result.error(CodeMsg.CUSTOMER_RELATION_FAILURE);

    }

    @ApiOperation(value = "删除客户联系人信息实体", notes = "已测试通过")
    @DeleteMapping("/delete")
    public Result delete(@RequestParam Long id) {
        Relation relation = relationService.selectById(id);
        if (null == relation) {
            return Result.error(new CodeMsg("找不到客户联系人信息"));
        }
        Relation primary = relationService.selectPrimaryByCid(relation.getCustomerId());
        if (Objects.isNull(primary)) {
            return Result.error(new CodeMsg("客户异常！找不到客户的主联系人"));
        }
        if (primary.getId().equals(relation.getId())) {
            return Result.error(new CodeMsg("不能删除客户的主联系人"));
        }

        if (followArrangeService.countByRelationIds(Collections.singletonList(id)) > 0) {
            return Result.error(new CodeMsg("客户联系人的跟进任务有相关记录，拒绝删除"));
        }
        if (followPlanService.countByRelationIds(Collections.singletonList(id)) > 0) {
            return Result.error(new CodeMsg("客户联系人的跟进计划有相关记录，拒绝删除"));
        }
        if (followService.countByRelationId(id) > 0) {
            return Result.error(new CodeMsg("客户联系人的跟进有相关记录，拒绝删除"));
        }

        Map<String, Integer> orderMap = orderBaseService.selectCountAllByRelationId(id);
        Integer orderBaseCount = orderMap.get("订单基本信息");
        Integer orderProductCount = orderMap.get("产品订单");
        Integer refundCount = orderMap.get("退款信息");
        Integer returnCount = orderMap.get("回款记录");
        if (null != orderBaseCount && orderBaseCount > 0) {
            return Result.error(new CodeMsg("客户联系人的订单基本信息有相关记录，拒绝删除"));
        }
        if (null != orderProductCount && orderProductCount > 0) {
            return Result.error(new CodeMsg("客户联系人的产品订单有相关记录，拒绝删除"));
        }
        if (null != refundCount && refundCount > 0) {
            return Result.error(new CodeMsg("客户联系人的退款信息有相关记录，拒绝删除"));
        }
        if (null != returnCount && returnCount > 0) {
            return Result.error(new CodeMsg("客户联系人的回款记录有相关记录，拒绝删除"));
        }

        if (complaintService.selCountByRelationId(id) > 0) {
            return Result.error(new CodeMsg("客户跟进任务有相关记录，拒绝删除"));
        }

        return relationService.deleteById(id) > 0 ?
                Result.success(null) : Result.error(CodeMsg.CUSTOMER_RELATION_FAILURE);

    }

    @ApiOperation("批量删除客户联系人")
    @DeleteMapping("deleteByIds")
    public Result deleteByIds(User user, @RequestBody List<Long> ids) {
        StringBuffer failStr = new StringBuffer();
        StringBuffer successStr = new StringBuffer();

        for (Long id : ids) {
            Relation relation = relationService.selectById(id);
            if (null == relation || Objects.isNull(relation.getRelationName())) {
                failStr.append("找不到客户联系人信息 id:").append(id).append("\n");
                continue;
            }
            Customer customer = customerService.selectById(relation.getCustomerId());
            if (null == customer || Objects.nonNull(customer.getCustomerName())) {
                failStr.append("找不到客户信息 id:").append(id).append("\n");
                continue;
            }
            Relation primary = relationService.selectPrimaryByCid(relation.getCustomerId());
            if (Objects.isNull(primary) || Objects.isNull(primary.getId())) {
                failStr.append("客户异常！找不到客户的主联系人").append(customer.getCustomerName()).append("\n");
                continue;
            }
            if (primary.getId().equals(relation.getId())) {
                failStr.append("不能删除客户的主联系人").append(customer.getCustomerName()).append("\n");
                continue;
            }
            if (followArrangeService.countByRelationIds(Collections.singletonList(id)) > 0) {
                failStr.append("客户联系人的跟进任务有相关记录").append(relation.getRelationName()).append("\n");
                continue;
            }
            if (followPlanService.countByRelationIds(Collections.singletonList(id)) > 0) {
                failStr.append("客户联系人的跟进计划有相关记录").append(relation.getRelationName()).append("\n");
                continue;
            }
            if (followService.countByRelationId(id) > 0) {
                failStr.append("客户联系人的跟进有相关记录").append(relation.getRelationName()).append("\n");
                continue;
            }

            Map<String, Integer> orderMap = orderBaseService.selectCountAllByRelationId(id);
            Integer orderBaseCount = orderMap.get("订单基本信息");
            Integer orderProductCount = orderMap.get("产品订单");
            Integer refundCount = orderMap.get("退款信息");
            Integer returnCount = orderMap.get("回款记录");
            if (null != orderBaseCount && orderBaseCount > 0) {
                failStr.append("客户联系人的订单基本信息有相关记录").append(relation.getRelationName()).append("\n");
                continue;
            }
            if (null != orderProductCount && orderProductCount > 0) {
                failStr.append("客户联系人的产品订单有相关记录").append(relation.getRelationName()).append("\n");
                continue;
            }
            if (null != refundCount && refundCount > 0) {
                failStr.append("客户联系人的退款信息有相关记录").append(relation.getRelationName()).append("\n");
                continue;
            }
            if (null != returnCount && returnCount > 0) {
                failStr.append("客户联系人的回款记录有相关记录").append(relation.getRelationName()).append("\n");
                continue;
            }
            if (complaintService.selCountByRelationId(id) > 0) {
                failStr.append("客户联系人跟进任务有相关记录").append(relation.getRelationName()).append("\n");
                continue;
            }

            if (relationService.deleteById(id) > 0) {
                successStr.append("客户联系人删除成功：").append(relation.getRelationName()).append("\n");
            } else {
                failStr.append("客户联系人删除失败").append(relation.getRelationName()).append("\n");
            }
        }

        return failStr.length() == 0 ? Result.success(successStr)
                : Result.error(new CodeMsg("Error message:" + failStr + "\nSuccess message:" + successStr));
    }

}

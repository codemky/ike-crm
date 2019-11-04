package com.ike.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ike.common.constans.CodeMsg;
import com.ike.common.result.Result;
import com.ike.common.util.ExcelUtil;
import com.ike.pojo.Customer;
import com.ike.pojo.Follow;
import com.ike.pojo.User;
import com.ike.pojo.vo.CustomerSearchVo;
import com.ike.pojo.vo.FollowDetailVo;
import com.ike.pojo.vo.FollowSearchVo;
import com.ike.pojo.vo.FollowVo;
import com.ike.service.CustomerService;
import com.ike.service.FollowService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.*;

/**
 * ClassName FollowController
 * Description TODO
 *
 * @author mokuanyuan
 * @version 1.0
 * @date 2019/10/15 11:35
 **/
@RestController
@RequestMapping("/follow")
@Api(description = "客户跟进模块")
public class FollowController {

    @Autowired
    HttpServletRequest request;
    @Autowired
    FollowService followService;
    @Autowired
    CustomerService customerService;


    @ApiOperation(value = "根据多种条件搜索客户跟进记录", notes = "")
    @GetMapping("/listByCriteria")
    public Result listByCriteria(User user,
                                 @RequestParam(value = "followWay", required = false) String followWay,
                                 @RequestParam(value = "customerStageId", required = false) String customerStageId,
                                 @RequestParam(value = "customerStatus", required = false) String customerStatus,
                                 @RequestParam(value = "employeeIds", required = false) String employeeIds,
                                 @RequestParam(value = "isMyself", required = false) String isMyself,
                                 @RequestParam(value = "customerName", required = false) String customerName,
                                 @RequestParam(value = "relationName", required = false) String relationName,
                                 @RequestParam(value = "relationPhone", required = false) String relationPhone,
                                 @RequestParam(value = "customerId", required = false) Long customerId,
                                 @RequestParam(value = "relationId", required = false) Long relationId,

                                 @RequestParam(value = "minTime", required = false) String minTime,
                                 @RequestParam(value = "maxTime", required = false) String maxTime,
                                 @RequestParam(value = "isToday", required = false) String isToday,
                                 @RequestParam(value = "yesterday", required = false) String yesterday,
                                 @RequestParam(value = "isWeek", required = false) String isWeek,
                                 @RequestParam(value = "isMouth", required = false) String isMouth,
                                 @RequestParam(value = "sortName", required = false) String sortName,
                                 @RequestParam(value = "sortType", required = false) String sortType,

                                 @RequestParam(value = "pageNum", defaultValue = "1", required = false) Integer pageNum,
                                 @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize) {

        FollowSearchVo criteria = FollowSearchVo.getProperties(request, new FollowSearchVo());
        if (criteria.getIsMyself() != null) {
            criteria.setEmployeeIds(Collections.singletonList(user.getId()));
        }

        IPage<FollowVo> voPage = followService.selectByCriteria(new Page<>(pageNum, pageSize), criteria);

        return Result.success(voPage);
    }

    @ApiOperation("添加跟进记录")
    @PostMapping("create")
    public Result create(User user, @RequestBody Follow follow) {

        Customer customer = customerService.selectById(follow.getCustomerId());
        if (customer == null) {
            return Result.error(500, "找不到该该客户信息");
        }

        follow.setCreateUserId(user.getId());
        follow.setCreateTime(LocalDateTime.now());
        follow.setEmployeeId(user.getId());
        follow.setCustomerStageId(customer.getCustomerStageId());
        follow.setCustomerStatus(customer.getCustomerState());

        return followService.create(follow) > 0 ? Result.success(null) : Result.error(CodeMsg.CUSTOMER_FOLLOW_FAILURE);

    }

    @ApiOperation("更新跟进记录")
    @PutMapping("update")
    public Result update(User user, @RequestBody Follow follow) {
        Customer customer = customerService.selectById(follow.getCustomerId());
        if (customer == null) {
            return Result.error(500, "找不到该该客户信息");
        }

        follow.setModifyUserId(user.getId());
        follow.setModifyTime(LocalDateTime.now());
        follow.setEmployeeId(user.getId());
        follow.setCustomerStageId(customer.getCustomerStageId());
        follow.setCustomerStatus(customer.getCustomerState());

        return followService.update(follow) > 0 ? Result.success(null) : Result.error(CodeMsg.CUSTOMER_FOLLOW_FAILURE);

    }

    @ApiOperation("获取跟进记录详情")
    @GetMapping("detail")
    public Result detail(User user, @RequestParam("id") Long id) {

        FollowDetailVo follow = followService.selectDetailVo(id);
        if (follow == null) {
            return Result.error(500, "找不到该该客户信息");
        }

        return Result.success(follow);
    }

    @ApiOperation("删除跟进记录")
    @DeleteMapping("delete")
    public Result delete(User user, @RequestParam("id") Long id) {

        User userOption = Optional.ofNullable(user).orElse(new User());
        if (userOption.getId() == null) {
            return Result.error(CodeMsg.SESSION_ERROR);
        }

        return followService.delete(id) > 0 ? Result.success(null) : Result.error(CodeMsg.CUSTOMER_FOLLOW_FAILURE);

    }

    @ApiOperation("批量删除跟进记录")
    @DeleteMapping("deleteByIds")
    public Result deleteByIds(User user, @RequestBody List<Long> ids) {

        return followService.deleteArrays(ids) > 0 ? Result.success(null) : Result.error(CodeMsg.CUSTOMER_FOLLOW_FAILURE);

    }

    @ApiOperation(value = "把跟进记录的Excel表数据导入到数据库(测试通过)", notes = "批量导入")
    @PostMapping("/import")
    public Result importExcel(User user, MultipartFile file) throws Exception {
        if (null == file) {
            return Result.error(new CodeMsg("文件读取错误, 无效的文件!"));
        }

        List<Object> list = ExcelUtil.readExcel(file, new Follow());

        if (null == list || list.size() == 0) {
            return Result.error(new CodeMsg("文件数据为空!"));
        }


        for (int i = 1; i <= list.size(); i++) {
            Follow follow = (Follow) list.get(i - 1);

            Customer customer = customerService.selectById(follow.getCustomerId());
            follow.setCustomerStatus(customer.getCustomerState());
            follow.setCustomerStageId(customer.getCustomerStageId());
            follow.setFollowTime(randomDate("2018-01-01 00:00:00","2019-10-12 00:00:00"));
            follow.setCreateUserId(follow.getEmployeeId());
            follow.setCreateTime(follow.getFollowTime());

            followService.create(follow);
        }



        return Result.success(CodeMsg.SUCCESS);
    }

    /**
     * 获取随机日期
     * @param beginDate 起始日期，格式为：yyyy-MM-dd HH:mm:ss
     * @param endDate 结束日期，格式为：yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static LocalDateTime randomDate(String beginDate,String endDate){
        try {
            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime begin = LocalDateTime.parse(beginDate, format);
            long beginMilli = begin.toInstant(ZoneOffset.of("+8")).toEpochMilli();

            LocalDateTime end = LocalDateTime.parse(endDate, format);
            long endMilli = end.toInstant(ZoneOffset.of("+8")).toEpochMilli();

            if(beginMilli >= endMilli ){
                return null;
            }

            long date = random(beginMilli,endMilli);

            return LocalDateTime.ofInstant(Instant.ofEpochMilli(date),ZoneOffset.of("+8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static long random(long begin,long end) {
        long rtn = begin + (long) (Math.random() * (end - begin));
        if (rtn == begin || rtn == end) {
            return random(begin, end);
        }
        return rtn;
    }

    }

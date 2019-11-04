package com.ike.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ike.common.constans.CodeMsg;
import com.ike.common.result.Result;
import com.ike.common.util.ServletBeanUtil;
import com.ike.pojo.vo.*;
import com.ike.service.StatisticsService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author wgm
 * @Date 2019/10/14 11:26
 */
@RestController
@Api(description = "统计模块")
@RequestMapping("/statistics")
public class StatisticsController{

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private StatisticsService statisticsService;

    @ApiOperation(value = "按时间统计新增客户数")
    @GetMapping("staCustomerAdd")
    public Result staCustomerAdd(@RequestParam(value = "dateType") String dateType,
                                 @RequestParam(value = "date", required = false) String date) {
        List<CustomerStatisticVO> statistic = statisticsService.staCustomerAdd(dateType, date);
        return Result.success(statistic);
    }

    @ApiOperation(value = "按时间统计客户跟进记录数")
    @GetMapping("staFollowAdd")
    public Result staFollowAdd(@RequestParam(value = "dateType") String dateType,
                               @RequestParam(value = "date", required = false)String date) {
        List<CustomerStatisticVO> statistic = statisticsService.staFollowAdd(dateType, date);
        return Result.success(statistic);
    }

    @ApiOperation(value = "按时间统计跟进的客户数")
    @GetMapping("staCustomerFollow")
    public Result staCustomerFollow(@RequestParam(value = "dateType") String dateType,
                                    @RequestParam(value = "date", required = false)String date) {
        List<CustomerStatisticVO> statistic = statisticsService.staCustomerFollow(dateType, date);
        return Result.success(statistic);
    }

    @ApiOperation(value = "按年月指定时间段统计产品的成交总额、成交数量、成交客户数")
    @GetMapping("staProduct")
    public Result staProduct(@RequestParam(value = "year",required = false) Integer year,
                             @RequestParam(value = "month",required = false) Integer month,
                             @RequestParam(value = "day",required = false) Integer day,
                             @RequestParam(value = "beginDate",required = false) String beginDate,
                             @RequestParam(value = "endDate",required = false) String endDate,
                             @RequestParam(value = "sortType",required = true) String sortType,
                             @RequestParam(value = "preId", required = false) Long preId,
                             @RequestParam(value = "pageNum",defaultValue = "1",required = false) Integer pageNum,
                             @RequestParam(value = "pageSize",defaultValue = "10",required = false) Integer pageSize){

        StaProSearchVO searchVO = StaProSearchVO.getProperties(request,new StaProSearchVO());
        Page<ProStatisticVO> page = new Page<>(pageNum,pageSize);
        switch (sortType) {
            case "name":
                return Result.success(statisticsService.staProductByName(page, searchVO));
            case "class":
                if (preId == null) return Result.error(new CodeMsg("PreId不能为空"));
                return Result.success(statisticsService.staProductByClass(searchVO));
            default:
                return Result.error(new CodeMsg("统计类型只能为name或class"));
        }

    }

    @ApiOperation(value = "根据条件，按时间统计订单某项金额的总和统计")
    @GetMapping("staOrderBaseTotal")
    public Result staOrderBaseTotal(
            @RequestParam(value = "year",required = false) Integer year,
            @RequestParam(value = "month",required = false) Integer month,
            @ApiParam(name = "staName", value = "要统计的字段（order_total为订单成交总额，order_cost为订单总成本，order_get_sum为订单回款总额，(order_actual_total - order_cost)为利润）", defaultValue = "order_total", required = false)
            @RequestParam(value = "staName",required = false) String staName,
            @ApiParam(name = "sortType", value = "展示方式（倒序DESC，正序ASC）", defaultValue = "DESC", required = false)
            @RequestParam(value = "sortType",required = false) String sortType,
            @ApiParam(name = "orderState", value = "订单状态（0未付款，1已付款，2回款中，3退款中，4退款完成，5表示删除状态）", defaultValue = "1", required = false)
            @RequestParam(value = "orderState",required = false) String orderState,
            @ApiParam(name = "employeeId", value = "订单负责人ID", required = true)
            @RequestParam(value = "employeeId",required = true) String employeeId){
        OrderBaseStatisitcVO orderBaseStatisitcVO = OrderBaseStatisitcVO.getProperties(request,new OrderBaseStatisitcVO());
        List<StatisticsVo> list = statisticsService.staOrderBaseTotal(orderBaseStatisitcVO);
        return list != null ?
            Result.success(list) : Result.error(CodeMsg.ERROR);
    }

    @ApiOperation(value = "根据条件，按时间段统计订单某项金额的总和统计")
    @GetMapping("staOrderBaseTotalByDateArea")
    public Result staOrderBaseTotalByDateArea(
            @ApiParam(name = "beginDate", value = "起始日期（格式为‘yyyy-mm-dd’）", required = false)
            @RequestParam(value = "beginDate",required = false) String beginDate,
            @ApiParam(name = "endDate", value = "结束日期（格式为‘yyyy-mm-dd’）", required = false)
            @RequestParam(value = "endDate",required = false) String endDate,
            @ApiParam(name = "dateCompany", value = "日期单位（即按日期区间统计时，返回日期的单位，'%Y'以年为单位，'%Y-%m'为以月为单位，'%Y-%m-%d'为以日为单位）", defaultValue = "%Y", required = false)
            @RequestParam(value = "dateCompany",defaultValue = "%Y",required = false) String dateCompany,
            @ApiParam(name = "staName", value = "要统计的字段（order_total为订单成交总额，order_cost为订单总成本，order_get_sum为订单回款总额，(order_actual_total - order_cost)为利润）", defaultValue = "order_total", required = false)
            @RequestParam(value = "staName",required = false) String staName,
            @ApiParam(name = "sortType", value = "展示方式（倒序DESC，正序ASC）", defaultValue = "DESC", required = false)
            @RequestParam(value = "sortType",required = false) String sortType,
            @ApiParam(name = "orderState", value = "订单状态（0未付款，1已付款，2回款中，3退款中，4退款完成，5表示删除状态）", defaultValue = "1", required = false)
            @RequestParam(value = "orderState",required = false) String orderState,
            @ApiParam(name = "employeeId", value = "订单负责人ID", required = true)
            @RequestParam(value = "employeeId",required = true) String employeeId){
        OrderBaseStatisitcVO orderBaseStatisitcVO = OrderBaseStatisitcVO.getProperties(request,new OrderBaseStatisitcVO());
        List<StatisticsVo> list = statisticsService.staOrderBaseTotalByDateArea(orderBaseStatisitcVO);
        return list != null ?
                Result.success(list) : Result.error(CodeMsg.ERROR);
    }

}

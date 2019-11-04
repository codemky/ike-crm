package com.ike.pojo.vo;

import com.ike.common.util.DateUtil;
import com.ike.common.util.ServletBeanUtil;
import lombok.Data;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * @Description TODO
 * @Date 2019/10/22 0:59
 */
@Data
public class ReturnDetailStatisitcVo {
    //时间
    private Integer year;
    private Integer month;
    private Integer day;
    //起始日期和结束日期（格式为yyyy-MM-dd）
    private String beginDate;
    private String endDate;
    //日期单位（即按日期区间统计时，返回日期的单位，'%Y'以年为单位，'%Y-%m'为以月为单位，'%Y-%m-%d'为以日为单位）
    private String dateCompany;
    //统计列名（order_total为订单成交总额，order_cost为订单总成本，order_get_sum为订单回款总额，(order_actual_total - order_cost)为利润）
    private String staName;
    //排序方式（ASC，DESC）
    private String sortType;
    //负责员工id
    private Long employeeId;
    //联系人名称
    private String relationName;
    //客户名称
    private String customerName;
    //日期集合
    private ArrayList<String> dateList;

    private String isToday;

    private String yesterday;

    private String preMouth;

    private String isMouth;


    public static ReturnDetailStatisitcVo getProperties(HttpServletRequest request, ReturnDetailStatisitcVo returnDetailStatisitcVo) {
        ServletBeanUtil.populate(returnDetailStatisitcVo, request);
        //默认为降序
        if (returnDetailStatisitcVo.getSortType() == null) {
            returnDetailStatisitcVo.setSortType("DESC");
        }
        // //获取月
        // if (returnDetailStatisitcVo.getYear() != null && returnDetailStatisitcVo.getMonth() == null) {
        //     returnDetailStatisitcVo.setDateList(DateUtil.getMonth());
        // } else if (returnDetailStatisitcVo.getYear() != null && returnDetailStatisitcVo.getMonth() != null && returnDetailStatisitcVo.getDay() == null) {
        //     //获取天数
        //     ArrayList<String> list = DateUtil.getDays(returnDetailStatisitcVo.getYear(), returnDetailStatisitcVo.getMonth());
        //     returnDetailStatisitcVo.setDateList(list);
        // }
        return returnDetailStatisitcVo;
    }


}

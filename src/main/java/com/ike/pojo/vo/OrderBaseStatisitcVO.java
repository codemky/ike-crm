package com.ike.pojo.vo;

import com.ike.common.util.DateUtil;
import com.ike.common.util.ServletBeanUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * @Date：2019-10-18 20:56
 * @Description：订单金额统计类
 */
public class OrderBaseStatisitcVO {
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
    //订单状态（0未付款，1已付款，2回款中，3退款中，4退款完成，5表示删除状态）
    private Integer orderState;
    //负责员工id
    private Long employeeId;
    //客户联系人id
    private Long relationId;



    //日期集合
    private ArrayList<String> dateList;

    public static OrderBaseStatisitcVO getProperties(HttpServletRequest request, OrderBaseStatisitcVO orderBaseStatisitcVO){
        ServletBeanUtil.populate(orderBaseStatisitcVO, request);
        //默认为统计成交总额
        if (orderBaseStatisitcVO.getStaName() == null) {
            orderBaseStatisitcVO.setStaName("order_total");
        }
        //默认为降序
        if (orderBaseStatisitcVO.getSortType() == null) {
            orderBaseStatisitcVO.setSortType("DESC");
        }
        //获取月
        if (orderBaseStatisitcVO.getYear() != null && orderBaseStatisitcVO.getMonth() == null) {
            orderBaseStatisitcVO.setDateList(DateUtil.getMonth());
        }else if (orderBaseStatisitcVO.getYear() != null && orderBaseStatisitcVO.getMonth() != null){
            //获取天数
            ArrayList<String> list = DateUtil.getDays(orderBaseStatisitcVO.getYear(),orderBaseStatisitcVO.getMonth());
            orderBaseStatisitcVO.setDateList(list);
        }
        //默认查询已完成订单
        if (orderBaseStatisitcVO.getOrderState() == null){
            orderBaseStatisitcVO.setOrderState(1);
        }

        return orderBaseStatisitcVO;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public ArrayList<String> getDateList() {
        return dateList;
    }

    public void setDateList(ArrayList<String> dateList) {
        this.dateList = dateList;
    }

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getDateCompany() {
        return dateCompany;
    }

    public void setDateCompany(String dateCompany) {
        this.dateCompany = dateCompany;
    }

    public String getStaName() {
        return staName;
    }

    public void setStaName(String staName) {
        this.staName = staName;
    }

    public Integer getOrderState() {
        return orderState;
    }

    public void setOrderState(Integer orderState) {
        this.orderState = orderState;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getSortType() {
        return sortType;
    }

    public void setSortType(String sortType) {
        this.sortType = sortType;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public Integer getDay() {
        return day;
    }
    public void setRelationId(Long relationId) {
        this.relationId = relationId;
    }

    public Long getRelationId() {
        return relationId;
    }
}

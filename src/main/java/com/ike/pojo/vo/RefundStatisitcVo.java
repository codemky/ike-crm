package com.ike.pojo.vo;

import com.ike.common.util.DateUtil;
import com.ike.common.util.ServletBeanUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * @Description TODO
 * @Date 2019/10/22 0:23
 */
public class RefundStatisitcVo {
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
    //日期集合
    private ArrayList<String> dateList;

    public static RefundStatisitcVo getProperties(HttpServletRequest request, RefundStatisitcVo refundStatisitcVo) {
        ServletBeanUtil.populate(refundStatisitcVo, request);
        //默认为降序
        if (refundStatisitcVo.getSortType() == null) {
            refundStatisitcVo.setSortType("DESC");
        }
        //获取月
        if (refundStatisitcVo.getYear() != null && refundStatisitcVo.getMonth() == null) {
            refundStatisitcVo.setDateList(DateUtil.getMonth());
        } else if (refundStatisitcVo.getYear() != null && refundStatisitcVo.getMonth() != null && refundStatisitcVo.getDay() == null) {
            //获取天数
            ArrayList<String> list = DateUtil.getDays(refundStatisitcVo.getYear(), refundStatisitcVo.getMonth());
            refundStatisitcVo.setDateList(list);
        }
        return refundStatisitcVo;
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

    public String getSortType() {
        return sortType;
    }

    public void setSortType(String sortType) {
        this.sortType = sortType;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public ArrayList<String> getDateList() {
        return dateList;
    }

    public void setDateList(ArrayList<String> dateList) {
        this.dateList = dateList;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }
}

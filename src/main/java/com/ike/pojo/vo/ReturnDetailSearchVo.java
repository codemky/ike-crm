package com.ike.pojo.vo;

import com.ike.common.util.ServletBeanUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @Description TODO
 * @Date 2019/10/20 22:11
 */
public class ReturnDetailSearchVo {
    private String isThisMonth;
    private String isLastMonth;
    private String isToday;
    private String isYesterday;
    //回款记录id
    private Long id;
    //客户id
    private Long customerId;
    //创建人ID
    private Long createUserId;
    //负责人ID
    private Long employeeId;
    //创建时间
    private Date createTimeMax;
    private Date createTimeMin;
    //联系人名称
    private String relationName;
    //客户名称
    private String customerName;
    //订单
    private Long orderBaseId;
    //创建时间
    private Date payTimeMax;
    private Date payTimeMin;
    //金额
    private Double amountMax;
    private Double amountMin;

    private String paymentType;

    private String sortType;

    public static ReturnDetailSearchVo getProperties(HttpServletRequest request, ReturnDetailSearchVo searchVO) {
        ServletBeanUtil.populate(searchVO, request);
        return searchVO;
    }

    public String getIsThisMonth() {
        return isThisMonth;
    }

    public void setIsThisMonth(String isThisMonth) {
        this.isThisMonth = isThisMonth;
    }

    public String getIsLastMonth() {
        return isLastMonth;
    }

    public void setIsLastMonth(String isLastMonth) {
        this.isLastMonth = isLastMonth;
    }

    public String getIsToday() {
        return isToday;
    }

    public void setIsToday(String isToday) {
        this.isToday = isToday;
    }

    public String getIsYesterday() {
        return isYesterday;
    }

    public void setIsYesterday(String isYesterday) {
        this.isYesterday = isYesterday;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Date getCreateTimeMax() {
        return createTimeMax;
    }

    public void setCreateTimeMax(Date createTimeMax) {
        this.createTimeMax = createTimeMax;
    }

    public Date getCreateTimeMin() {
        return createTimeMin;
    }

    public void setCreateTimeMin(Date createTimeMin) {
        this.createTimeMin = createTimeMin;
    }

    public Long getOrderBaseId() {
        return orderBaseId;
    }

    public void setOrderBaseId(Long orderBaseId) {
        this.orderBaseId = orderBaseId;
    }

    public Date getPayTimeMax() {
        return payTimeMax;
    }

    public void setPayTimeMax(Date payTimeMax) {
        this.payTimeMax = payTimeMax;
    }

    public Date getPayTimeMin() {
        return payTimeMin;
    }

    public void setPayTimeMin(Date payTimeMin) {
        this.payTimeMin = payTimeMin;
    }

    public Double getAmountMax() {
        return amountMax;
    }

    public void setAmountMax(Double amountMax) {
        this.amountMax = amountMax;
    }

    public Double getAmountMin() {
        return amountMin;
    }

    public void setAmountMin(Double amountMin) {
        this.amountMin = amountMin;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getSortType() {
        return sortType;
    }

    public void setSortType(String sortType) {
        this.sortType = sortType;
    }

    public String getRelationName() {
        return relationName;
    }

    public void setRelationName(String relationName) {
        this.relationName = relationName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
}

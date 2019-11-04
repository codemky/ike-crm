package com.ike.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ike.pojo.Refund;

import java.time.LocalDateTime;

//@Data
public class RefundVo extends Refund {

    private String refundDateStr;
    /**
     * 客户id
     */
    private Long customerId;
    private String customerName;
    private String relationName;
    private String employeeName;

    /**
     * 订单成交总额
     **/
    private Double orderTotal;

    /**
     * 订单下单时间
     **/
    @JsonFormat( pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime orderTime;

    /**
     * 订单的各种信息 格式为：（时间：MM-dd ; 商品1：价格；商品2：价格；商品3：价格）
     */
    private String orderBaseMessages;

    public String getRefundDateStr() {
        return refundDateStr;
    }

    public void setRefundDateStr(String refundDateStr) {
        this.refundDateStr = refundDateStr;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getRelationName() {
        return relationName;
    }

    public void setRelationName(String relationName) {
        this.relationName = relationName;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public Double getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(Double orderTotal) {
        this.orderTotal = orderTotal;
    }

    public LocalDateTime getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(LocalDateTime orderTime) {
        this.orderTime = orderTime;
    }

    public String getOrderBaseMessages() {
        return orderBaseMessages;
    }

    public void setOrderBaseMessages(String orderBaseMessages) {
        this.orderBaseMessages = orderBaseMessages;
    }
}

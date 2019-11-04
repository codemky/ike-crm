package com.ike.pojo.vo;

import com.ike.pojo.ReturnDetail;

/**
 * @Description TODO
 * @Date 2019/10/20 22:37
 */
public class ReturnDetailVo extends ReturnDetail {

    //客户id
    private Long customerId;
    private String relationName;
    private String customerName;
    private String employeeName;
    private String payTimeStr;
    //订单的信息
    private String orderbaseMessage;

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
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

    public String getOrderbaseMessage() {
        return orderbaseMessage;
    }

    public void setOrderbaseMessage(String orderbaseMessage) {
        this.orderbaseMessage = orderbaseMessage;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPayTimeStr() {
        return payTimeStr;
    }

    public void setPayTimeStr(String payTimeStr) {
        this.payTimeStr = payTimeStr;
    }
}

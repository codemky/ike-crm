package com.ike.pojo.vo;

/**
 * @Author wuchuxin
 * @Date 2019/10/21 10:10
 * @Version 1.0
 */
public class StaProductClassVO {
    private Long id;
    private String className;
    private Long preClassId;
    private Double totalPrice;
    private Integer totalSum;
    private Integer customerTimes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Long getPreClassId() {
        return preClassId;
    }

    public void setPreClassId(Long preClassId) {
        this.preClassId = preClassId;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getTotalSum() {
        return totalSum;
    }

    public void setTotalSum(Integer totalSum) {
        this.totalSum = totalSum;
    }

    public Integer getCustomerTimes() {
        return customerTimes;
    }

    public void setCustomerTimes(Integer customerTimes) {
        this.customerTimes = customerTimes;
    }
}

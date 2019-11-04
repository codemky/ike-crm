package com.ike.pojo.vo;

/**
 * @Author wuchuxin
 * @year 2019/10/14 10:29
 * @Version 1.0
 */
public class ProStatisticVO {
    //日期
    private Integer date;

    //产品名称
    private String ProductName;
    
    //成交客户数量
    private Integer customerTimes;

    //产品成交数量
    private Integer orderNum;

    //产品成交总额
    private Double orderCount;

    public Integer getDate() {
        return date;
    }

    public void setDate(Integer date) {
        this.date = date;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public Integer getCustomerTimes() {
        return customerTimes;
    }

    public void setCustomerTimes(Integer customerTimes) {
        this.customerTimes = customerTimes;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public Double getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(Double orderCount) {
        this.orderCount = orderCount;
    }

}

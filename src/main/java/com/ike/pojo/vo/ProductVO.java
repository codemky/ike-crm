package com.ike.pojo.vo;

import java.time.LocalDateTime;

/**
 * @Author wuchuxin
 * @Date 2019/10/10 17:18
 * @Version 1.0
 */
public class ProductVO {
    //产品id
    private Long id;
    //录入人id
    private Long createUserId;
    //产品名称
    private String productName;
    //成交数量
    private Integer orderNum;
    //成交总额度
    private Integer orderCount;
    //成交成本总数
    private Double orderCost;
    //产品总利润
    private Double orderProfit;
    //成交次数
    private Integer orderTimes;
    //录入时间
    private LocalDateTime createTime;
    //录入人
    private String createUserName;
    //成交客户数
//    private Integer customerTimes;
    //销售状态  0:下架    1:上架
    private Byte onSale;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public Integer getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(Integer orderCount) {
        this.orderCount = orderCount;
    }

    public Double getOrderCost() {
        return orderCost;
    }

    public void setOrderCost(Double orderCost) {
        this.orderCost = orderCost;
    }

    public Double getOrderProfit() {
        return orderProfit;
    }

    public void setOrderProfit(Double orderProfit) {
        this.orderProfit = orderProfit;
    }

    public Integer getOrderTimes() {
        return orderTimes;
    }

    public void setOrderTimes(Integer orderTimes) {
        this.orderTimes = orderTimes;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

//    public Integer getCustomerTimes() {
//        return customerTimes;
//    }
//
//    public void setCustomerTimes(Integer customerTimes) {
//        this.customerTimes = customerTimes;
//    }

    public Byte getOnSale() {
        return onSale;
    }

    public void setOnSale(Byte onSale) {
        this.onSale = onSale;
    }

    @Override
    public String toString() {
        return "ProductVO{" +
                "id=" + id +
                ", createUserId=" + createUserId +
                ", productName='" + productName + '\'' +
                ", orderNum=" + orderNum +
                ", orderCount=" + orderCount +
                ", orderCost=" + orderCost +
                ", orderProfit=" + orderProfit +
                ", orderTimes=" + orderTimes +
                ", createTime=" + createTime +
                ", createUserName='" + createUserName + '\'' +
                ", onSale=" + onSale +
                '}';
    }
}

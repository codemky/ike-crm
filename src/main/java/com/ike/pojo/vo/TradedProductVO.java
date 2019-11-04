package com.ike.pojo.vo;

import java.time.LocalDateTime;

/**
 * @Author wuchuxin
 * @Date 2019/10/12 8:51
 * @Version 1.0
 */
public class TradedProductVO {
    //成交时间
    private LocalDateTime orderTime;

    //成本
    private Double orderCost;

    //销售单价
    private Double salePrice;

    //成交数量
    private Integer orderCount;

    //实际成交价(元)
    private Double orderActualTotal;

    //备注
    private String note;

    public LocalDateTime getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(LocalDateTime orderTime) {
        this.orderTime = orderTime;
    }

    public Double getOrderCost() {
        return orderCost;
    }

    public void setOrderCost(Double orderCost) {
        this.orderCost = orderCost;
    }

    public Double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(Double salePrice) {
        this.salePrice = salePrice;
    }

    public Integer getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(Integer orderCount) {
        this.orderCount = orderCount;
    }

    public Double getOrderActualTotal() {
        return orderActualTotal;
    }

    public void setOrderActualTotal(Double orderActualTotal) {
        this.orderActualTotal = orderActualTotal;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "TradedProductVO{" +
                "orderTime=" + orderTime +
                ", orderCost=" + orderCost +
                ", salePrice=" + salePrice +
                ", orderCount=" + orderCount +
                ", orderActualTotal=" + orderActualTotal +
                ", note='" + note + '\'' +
                '}';
    }
}

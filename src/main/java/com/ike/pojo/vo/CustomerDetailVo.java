package com.ike.pojo.vo;

import com.ike.pojo.Customer;

import java.time.LocalDateTime;

/**
 * ClassName CustomerDetailVo
 * Description TODO
 *
 * @author mokuanyuan
 * @version 1.0
 * @date 2019/10/17 17:04
 **/
public class CustomerDetailVo extends Customer {

    private String stageName;
    private String levelName;
    private String originName;
    private String employeeName;
    private Long followCount;
    private LocalDateTime lastFollowTime;
    private Long followArrangeCount;
    private Long followPlanCount;
    private Long orderCount;
    private Long productCount;
    private Double orderSum;
    private Double costSum;
    //欠款 成交总额-总回款
    private Double debt;
    private LocalDateTime lastReturnTime;
    private Long returnCount;
    private Double returnSum;
    private Double refundSum;
    private Long refundCount;
    //利润 成交总额-总成本
    private Double profit;
    private Long complainCount;
    private String createName;
    private String modifyName;

    public Double getCostSum() {
        return costSum;
    }

    public void setCostSum(Double costSum) {
        this.costSum = costSum;
    }

    public String getStageName() {
        return stageName;
    }

    public void setStageName(String stageName) {
        this.stageName = stageName;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public String getOriginName() {
        return originName;
    }

    public void setOriginName(String originName) {
        this.originName = originName;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public Long getFollowCount() {
        return followCount;
    }

    public void setFollowCount(Long followCount) {
        this.followCount = followCount;
    }

    public LocalDateTime getLastFollowTime() {
        return lastFollowTime;
    }

    public void setLastFollowTime(LocalDateTime lastFollowTime) {
        this.lastFollowTime = lastFollowTime;
    }

    public Long getFollowArrangeCount() {
        return followArrangeCount;
    }

    public void setFollowArrangeCount(Long followArrangeCount) {
        this.followArrangeCount = followArrangeCount;
    }

    public Long getFollowPlanCount() {
        return followPlanCount;
    }

    public void setFollowPlanCount(Long followPlanCount) {
        this.followPlanCount = followPlanCount;
    }

    public Long getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(Long orderCount) {
        this.orderCount = orderCount;
    }

    public Long getProductCount() {
        return productCount;
    }

    public void setProductCount(Long productCount) {
        this.productCount = productCount;
    }

    public Double getOrderSum() {
        return orderSum;
    }

    public void setOrderSum(Double orderSum) {
        this.orderSum = orderSum;
    }

    public Double getReturnSum() {
        return returnSum;
    }

    public void setReturnSum(Double returnSum) {
        this.returnSum = returnSum;
    }

    public Double getDebt() {
        return debt;
    }

    public void setDebt(Double debt) {
        this.debt = debt;
    }

    public LocalDateTime getLastReturnTime() {
        return lastReturnTime;
    }

    public void setLastReturnTime(LocalDateTime lastReturnTime) {
        this.lastReturnTime = lastReturnTime;
    }

    public Long getReturnCount() {
        return returnCount;
    }

    public void setReturnCount(Long returnCount) {
        this.returnCount = returnCount;
    }

    public Double getRefundSum() {
        return refundSum;
    }

    public void setRefundSum(Double refundSum) {
        this.refundSum = refundSum;
    }

    public Long getRefundCount() {
        return refundCount;
    }

    public void setRefundCount(Long refundCount) {
        this.refundCount = refundCount;
    }

    public Double getProfit() {
        return profit;
    }

    public void setProfit(Double profit) {
        this.profit = profit;
    }

    public Long getComplainCount() {
        return complainCount;
    }

    public void setComplainCount(Long complainCount) {
        this.complainCount = complainCount;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public String getModifyName() {
        return modifyName;
    }

    public void setModifyName(String modifyName) {
        this.modifyName = modifyName;
    }
}

package com.ike.pojo;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

import java.time.LocalDateTime;

public class Follow extends BaseRowModel {
    private Long id;

    @ExcelProperty(index = 3, value = "员工id")
    private Long employeeId;
    @ExcelProperty(index = 0, value = "客户id")
    private Long customerId;
    @ExcelProperty(index = 1, value = "联系人id")
    private Long relationId;
    @ExcelProperty(index = 2, value = "跟进方式")
    private String followWay;
    private LocalDateTime followTime;
    @ExcelProperty(index = 4, value = "跟进细节")
    private String followDetail;
    @ExcelProperty(index = 5, value = "跟进结果")
    private String followResult;

    private Long customerStageId;

    private String customerStatus;

    private Long createUserId;

    private LocalDateTime createTime;

    private Long modifyUserId;

    private LocalDateTime modifyTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getRelationId() {
        return relationId;
    }

    public void setRelationId(Long relationId) {
        this.relationId = relationId;
    }

    public String getFollowWay() {
        return followWay;
    }

    public void setFollowWay(String followWay) {
        this.followWay = followWay == null ? null : followWay.trim();
    }

    public LocalDateTime getFollowTime() {
        return followTime;
    }

    public void setFollowTime(LocalDateTime followTime) {
        this.followTime = followTime;
    }

    public String getFollowDetail() {
        return followDetail;
    }

    public void setFollowDetail(String followDetail) {
        this.followDetail = followDetail == null ? null : followDetail.trim();
    }

    public String getFollowResult() {
        return followResult;
    }

    public void setFollowResult(String followResult) {
        this.followResult = followResult == null ? null : followResult.trim();
    }

    public Long getCustomerStageId() {
        return customerStageId;
    }

    public void setCustomerStageId(Long customerStageId) {
        this.customerStageId = customerStageId;
    }

    public String getCustomerStatus() {
        return customerStatus;
    }

    public void setCustomerStatus(String customerStatus) {
        this.customerStatus = customerStatus == null ? null : customerStatus.trim();
    }

    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public Long getModifyUserId() {
        return modifyUserId;
    }

    public void setModifyUserId(Long modifyUserId) {
        this.modifyUserId = modifyUserId;
    }

    public LocalDateTime getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(LocalDateTime modifyTime) {
        this.modifyTime = modifyTime;
    }
}
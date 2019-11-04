package com.ike.pojo;

import com.ike.pojo.vo.CustomerSearchVo;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

public class Customer {
    private Long id;

    private String customerName;

    private Long employeeId;

    private Long customerStageId;

    private Long customerLevelId;

    private String customerState;

    private Long customerOriginId;

    private String customerIntroduce;

    private String customerAddress;

    private Long createUserId;

    private LocalDateTime createTime;

    private Long modifyUserId;

    private LocalDateTime modifyTime;


    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", customerName='" + customerName + '\'' +
                ", employeeId=" + employeeId +
                ", customerStageId=" + customerStageId +
                ", customerLevelId=" + customerLevelId +
                ", customerState='" + customerState + '\'' +
                ", customerOriginId=" + customerOriginId +
                ", customerIntroduce='" + customerIntroduce + '\'' +
                ", customerAddress='" + customerAddress + '\'' +
                ", createUserId=" + createUserId +
                ", createTime=" + createTime +
                ", modifyUserId=" + modifyUserId +
                ", modifyTime=" + modifyTime +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName == null ? null : customerName.trim();
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Long getCustomerStageId() {
        return customerStageId;
    }

    public void setCustomerStageId(Long customerStageId) {
        this.customerStageId = customerStageId;
    }

    public Long getCustomerLevelId() {
        return customerLevelId;
    }

    public void setCustomerLevelId(Long customerLevelId) {
        this.customerLevelId = customerLevelId;
    }

    public String getCustomerState() {
        return customerState;
    }

    public void setCustomerState(String customerState) {
        this.customerState = customerState == null ? null : customerState.trim();
    }

    public Long getCustomerOriginId() {
        return customerOriginId;
    }

    public void setCustomerOriginId(Long customerOriginId) {
        this.customerOriginId = customerOriginId;
    }

    public String getCustomerIntroduce() {
        return customerIntroduce;
    }

    public void setCustomerIntroduce(String customerIntroduce) {
        this.customerIntroduce = customerIntroduce == null ? null : customerIntroduce.trim();
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress == null ? null : customerAddress.trim();
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
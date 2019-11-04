package com.ike.pojo.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

/**
 * ClassName CustomerImportVo
 * Description TODO
 *
 * @author mokuanyuan
 * @version 1.0
 * @date 2019/10/22 8:52
 **/
public class CustomerImportVo extends BaseRowModel {

    @ExcelProperty(index = 0, value = "客户名称")
    private String customerName;
    @ExcelProperty(index = 1, value = "客户状态")
    private String customerState;
    @ExcelProperty(index = 2, value = "客户阶段")
    private String customerStage;
    @ExcelProperty(index = 3, value = "客户等级")
    private String customerLevel;
    @ExcelProperty(index = 4, value = "客户来源")
    private String customerOrigin;
    @ExcelProperty(index = 5, value = "客户地址")
    private String customerAddress;
    @ExcelProperty(index = 6, value = "客户介绍")
    private String customerIntroduce;
    @ExcelProperty(index = 7, value = "[联系人]姓名")
    private String relationName;
    @ExcelProperty(index = 8, value = "[联系人]性别")
    private String sex;
    @ExcelProperty(index = 9, value = "[联系人]职务")
    private String relationPosition;
    @ExcelProperty(index = 10, value = "[联系人]称呼")
    private String relationCall;
    @ExcelProperty(index = 11, value = "[联系人]手机号")
    private String relationPhone;
    @ExcelProperty(index = 12, value = "[联系人]座机")
    private String landlineNumber;
    @ExcelProperty(index = 13, value = "[联系人]邮箱")
    private String relationEmail;

    private Long customerStageId;
    private Long customerLevelId;
    private Long customerOriginId;
    private Boolean relationSex;

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerState() {
        return customerState;
    }

    public void setCustomerState(String customerState) {
        this.customerState = customerState;
    }

    public String getCustomerStage() {
        return customerStage;
    }

    public void setCustomerStage(String customerStage) {
        this.customerStage = customerStage;
    }

    public String getCustomerLevel() {
        return customerLevel;
    }

    public void setCustomerLevel(String customerLevel) {
        this.customerLevel = customerLevel;
    }

    public String getCustomerOrigin() {
        return customerOrigin;
    }

    public void setCustomerOrigin(String customerOrigin) {
        this.customerOrigin = customerOrigin;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCustomerIntroduce() {
        return customerIntroduce;
    }

    public void setCustomerIntroduce(String customerIntroduce) {
        this.customerIntroduce = customerIntroduce;
    }

    public String getRelationName() {
        return relationName;
    }

    public void setRelationName(String relationName) {
        this.relationName = relationName;
    }

    public Boolean getRelationSex() {
        return relationSex;
    }

    public void setRelationSex(Boolean relationSex) {
        this.relationSex = relationSex;
    }

    public String getRelationPosition() {
        return relationPosition;
    }

    public void setRelationPosition(String relationPosition) {
        this.relationPosition = relationPosition;
    }

    public String getRelationCall() {
        return relationCall;
    }

    public void setRelationCall(String relationCall) {
        this.relationCall = relationCall;
    }

    public String getRelationPhone() {
        return relationPhone;
    }

    public void setRelationPhone(String relationPhone) {
        this.relationPhone = relationPhone;
    }

    public String getLandlineNumber() {
        return landlineNumber;
    }

    public void setLandlineNumber(String landlineNumber) {
        this.landlineNumber = landlineNumber;
    }

    public String getRelationEmail() {
        return relationEmail;
    }

    public void setRelationEmail(String relationEmail) {
        this.relationEmail = relationEmail;
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

    public Long getCustomerOriginId() {
        return customerOriginId;
    }

    public void setCustomerOriginId(Long customerOriginId) {
        this.customerOriginId = customerOriginId;
    }
}

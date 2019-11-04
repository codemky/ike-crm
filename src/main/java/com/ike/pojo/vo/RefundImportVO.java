package com.ike.pojo.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import com.ike.common.util.Converter;
import com.ike.common.util.ImportTestUtil;
import com.ike.pojo.Refund;

/**
 * @Date：2019-10-24 21:42
 * @Description：回款导入VO
 */
public class RefundImportVO extends BaseRowModel {

    @ExcelProperty(value = "负责人id", index = 0)
    private String employeeId;

    @ExcelProperty(value = "联系人id", index = 1)
    private String relationId;

    @ExcelProperty(value = "订单id", index = 2)
    private String orderBaseId;

    @ExcelProperty(value = "退款时间", index = 3)
    private String refundDate;

    @ExcelProperty(value = "退款金额", index = 4)
    private String refundAmount;

    @ExcelProperty(value = "退款方式", index = 5)
    private String refundMethod;

    @ExcelProperty(value = "备注", index = 6)
    private String remark;

    public static Refund getProperties(Refund refund, RefundImportVO importVO){
        refund.setEmployeeId(Converter.toLong(importVO.getEmployeeId()));
        refund.setRelationId(Converter.toLong(importVO.getRelationId()));
        refund.setOrderBaseId(Converter.toLong(importVO.getOrderBaseId()));

        //获取退款时间
        refund.setRefundDate(ImportTestUtil.parseLocalDateTime(importVO.getRefundDate()));
        //获取退款金额
        refund.setRefundAmount(Converter.toDouble(importVO.getRefundAmount()));
        //获取退款方式
        refund.setRefundMethod(importVO.getRefundMethod());
        refund.setRemark(importVO.getRemark());

        return refund;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getRelationId() {
        return relationId;
    }

    public void setRelationId(String relationId) {
        this.relationId = relationId;
    }

    public String getOrderBaseId() {
        return orderBaseId;
    }

    public void setOrderBaseId(String orderBaseId) {
        this.orderBaseId = orderBaseId;
    }

    public String getRefundDate() {
        return refundDate;
    }

    public void setRefundDate(String refundDate) {
        this.refundDate = refundDate;
    }

    public String getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(String refundAmount) {
        this.refundAmount = refundAmount;
    }

    public String getRefundMethod() {
        return refundMethod;
    }

    public void setRefundMethod(String refundMethod) {
        this.refundMethod = refundMethod;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}

package com.ike.pojo.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import com.ike.common.util.Converter;
import com.ike.common.util.ImportTestUtil;
import com.ike.pojo.ReturnDetail;
import com.ike.pojo.User;

import java.time.LocalDateTime;

/**
 * @Date：2019-10-23 22:47
 * @Description：回款明细导入VO
 */
public class ReturnDetailImportVO extends BaseRowModel{

    @ExcelProperty(value = "负责人id", index = 0)
    private String employeeId;

    @ExcelProperty(value = "联系人id", index = 1)
    private String relationId;

    @ExcelProperty(value = "订单id", index = 2)
    private String orderBaseId;

    @ExcelProperty(value = "支付时间", index = 3)
    private String payTime;

    @ExcelProperty(value = "支付金额", index = 4)
    private String amount;

    @ExcelProperty(value = "支付方式", index = 5)
    private String payment_type;

    @ExcelProperty(value = "备注", index = 6)
    private String remark;

    public static ReturnDetail getProperties(ReturnDetail returnDetail, ReturnDetailImportVO importVO){
        returnDetail.setEmployeeId(Converter.toLong(importVO.getEmployeeId()));
        returnDetail.setRelationId(Converter.toLong(importVO.getRelationId()));
        returnDetail.setOrderBaseId(Converter.toLong(importVO.getOrderBaseId()));

        //获取订单时间
        returnDetail.setPayTime(ImportTestUtil.parseLocalDateTime(importVO.getPayTime()));

        returnDetail.setAmount(Converter.toDouble(importVO.getAmount()));
        returnDetail.setPaymentType(importVO.getPayment_type());
        returnDetail.setRemark(importVO.getRemark());

        return returnDetail;
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

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getPayment_type() {
        return payment_type;
    }

    public void setPayment_type(String payment_type) {
        this.payment_type = payment_type;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}

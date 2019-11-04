package com.ike.pojo.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import com.ike.common.util.Converter;
import com.ike.pojo.OrderBase;

import java.time.LocalDateTime;

/**
 * @Date：2019-10-22 22:08
 * @Description：订单信息导入VO
 */
public class OrderBaseImportVO extends BaseRowModel {

    @ExcelProperty(value = "负责人id", index = 0)
    private String employeeId;

    @ExcelProperty(value = "联系人id", index = 1)
    private String relationId;

    @ExcelProperty(value = "备注", index = 2)
    private String note;

    @ExcelProperty(value = "订单金额", index = 3)
    private String orderTotal;

    @ExcelProperty(value = "订单实际总额", index = 4)
    private String orderActualTotal;

    @ExcelProperty(value = "订单成本", index = 5)
    private String orderCost;

    @ExcelProperty(value = "支付方式", index = 6)
    private String paymentMethod;

    public static OrderBase getProperties(OrderBase orderBase, OrderBaseImportVO importVO){
        orderBase.setEmployeeId(Converter.toLong(importVO.getEmployeeId()));
        orderBase.setRelationId(Converter.toLong(importVO.getRelationId()));
        orderBase.setNote(importVO.getNote());
        orderBase.setOrderTotal(Converter.toDouble(importVO.getOrderTotal()));
        orderBase.setOrderActualTotal(Converter.toDouble(importVO.getOrderActualTotal()));
        orderBase.setOrderCost(Converter.toDouble(importVO.getOrderCost()));
        orderBase.setPaymentMethod(importVO.getPaymentMethod());

        //下单时间
        LocalDateTime time = LocalDateTime.now();
        orderBase.setOrderTime(time);
        //订单状态为待付款
        orderBase.setOrderState((byte) 0);

        return orderBase;
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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(String orderTotal) {
        this.orderTotal = orderTotal;
    }

    public String getOrderActualTotal() {
        return orderActualTotal;
    }

    public void setOrderActualTotal(String orderActualTotal) {
        this.orderActualTotal = orderActualTotal;
    }

    public String getOrderCost() {
        return orderCost;
    }

    public void setOrderCost(String orderCost) {
        this.orderCost = orderCost;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

}

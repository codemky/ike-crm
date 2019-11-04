package com.ike.pojo.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @Date: 2019-10-22 22:23
 * @Description:(描述)
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RefundExportVo extends BaseRowModel {

    @ExcelProperty(value = "编号", index = 0)
    private Long refundId;
    @ExcelProperty(value = "客户", index = 1)
    private String customerName;
    @ExcelProperty(value = "成交订单", index = 2)
    private Long orderBaseId;
    @ExcelProperty(value = "关联回款", index = 3)
    private Double returnMoney;
    @ExcelProperty(value = "退款时间", index = 4)
    private LocalDateTime refundDate;
    @ExcelProperty(value = "退款金额", index = 5)
    private Double refundAmount;
    @ExcelProperty(value = "付款方式", index = 6)
    private String refundMethod;
    @ExcelProperty(value = "备注", index = 7)
    private String remark;
    @ExcelProperty(value = "录入人", index = 8)
    private String userName;
    @ExcelProperty(value = "录入时间", index = 9)
    private LocalDateTime createTime;
    @ExcelProperty(value = "负责人", index = 10)
    private String employeeName;
    @ExcelProperty(value = "所属部门", index = 11)
    private String departmentName;
}

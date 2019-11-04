package com.ike.pojo.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @Date: 2019-10-24 19:41
 * @Description:(描述) 订单导出帮助类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderExportVo extends BaseRowModel {

    @ExcelProperty(value = "编号", index = 0)
    private Long orderBaseId;
    @ExcelProperty(value = "成交客户", index = 1)
    private String customerName;
    @ExcelProperty(value = "成交产品-产品名称", index = 2)
    private String productName;
    @ExcelProperty(value = "成交产品-成本", index = 3)
    private Double cost;
    @ExcelProperty(value = "成交产品-销售单价", index = 4)
    private Double salePrice;
    @ExcelProperty(value = "成交产品-成交数量", index = 5)
    private Integer orderCount;
    @ExcelProperty(value = "成交产品-总价", index = 6)
    private Double totalPrice;
    @ExcelProperty(value = "订单备注", index = 7)
    private String note;
    @ExcelProperty(value = "订单成交总额", index = 8)
    private Double orderTotal;
    @ExcelProperty(value = "订单实际成交总额", index = 9)
    private Double orderActualTotal;
    @ExcelProperty(value = "订单总成本", index = 10)
    private Double orderCost;
    @ExcelProperty(value = "成交/签约时间", index = 11)
    private LocalDateTime orderTimel;
    @ExcelProperty(value = "付款方式", index = 12)
    private String paymentMethod;
    @ExcelProperty(value = "订单状态", index = 13)
    private Byte orderState;
    @ExcelProperty(value = "利润", index = 14)
    private Double profit;
    @ExcelProperty(value = "录入时间", index = 15)
    private LocalDateTime createTime;
    @ExcelProperty(value = "录入人（创建人）", index = 16)
    private String createUserName;
    @ExcelProperty(value = "负责人", index = 17)
    private String relationName;
    @ExcelProperty(value = "回款总额", index = 18)
    private Double orderGetSum;
    @ExcelProperty(value = "已回款期数", index = 19)
    private String ratio;
    @ExcelProperty(value = "退款总额", index = 20)
    private Double refundAmount;
    @ExcelProperty(value = "欠款",index = 21)
    private Double debt;
    @ExcelProperty(value = "最后回款时间",index = 22)
    private LocalDateTime maxTime;
    @ExcelProperty(value = "最后回款金额",index = 23)
    private Double lastAmount;
    // @ExcelProperty(value = "回款总次数",index = 24)
    // private Integer sumAcount;
    @ExcelProperty(value = "所属部门",index = 25)
    private String departmentName;
    @ExcelProperty(value = "收货人（客户）地址",index = 26)
    private String customerAddress;

}

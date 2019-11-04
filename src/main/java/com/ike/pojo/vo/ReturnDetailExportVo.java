package com.ike.pojo.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.tomcat.util.modeler.BaseModelMBean;

import java.time.LocalDateTime;

/**
 * @Date: 2019-10-25 21:20
 * @Description:(描述)
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReturnDetailExportVo extends BaseRowModel {
    @ExcelProperty(value = "编号", index = 0)
    private Long returnDetail;
    @ExcelProperty(value = "客户姓名", index = 1)
    private String userName;
    @ExcelProperty(value = "成交订单", index = 2)
    private Long orderBaseId;
    @ExcelProperty(value = "回款时间",index = 3)
    private LocalDateTime payTime;
    @ExcelProperty(value = "期次",index = 4)
    private Integer sumCount;
    @ExcelProperty(value = "已回款金额",index = 5)
    private Double sumPay;
    @ExcelProperty(value = "付款方式",index = 6)
    private String paymentType;
    @ExcelProperty(value = "备注",index = 7)
    private String remark;
    @ExcelProperty(value = "需回款总金额",index = 8)
    private Double orderGetSum;
    @ExcelProperty(value = "最后回款时间",index = 9)
    private LocalDateTime lastPayTime;
    @ExcelProperty(value = "最后回款金额",index = 10)
    private Double lastAmount;
    @ExcelProperty(value = "录入人",index = 11)
    private String createUserName;
    @ExcelProperty(value = "录入时间",index = 12)
    private LocalDateTime createTime;
    @ExcelProperty(value = "负责人",index = 13)
    private String employeeName;
    @ExcelProperty(value = "所属部门（负责人所属）",index = 14)
    private String departmentName;
}

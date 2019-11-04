package com.ike.pojo.vo;

import com.ike.pojo.OrderBase;
import lombok.Data;

/**
 * @Description TODO
 * @Date 2019/10/18 11:17
 */
@Data
public class OrderBaseVo extends OrderBase {

    private String orderTimeStr;

    //负责员工名
    private String employeeName;

    //客户联系人名
    private String relationName;

    //客户id
    private Long customerId;

    //客户名
    private String customerName;

    //已回款期数
    private String returnDetailCount;

    //利润
    private String profit;

}

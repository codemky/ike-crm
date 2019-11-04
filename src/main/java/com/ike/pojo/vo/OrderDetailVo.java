package com.ike.pojo.vo;

import com.ike.pojo.OrderBase;
import lombok.Data;

import java.util.List;

/**
 * ClassName OrderDetailVo
 * Description TODO
 *
 * @author mokuanyuan
 * @version 1.0
 * @date 2019/10/26 15:43
 **/
@Data
public class OrderDetailVo extends OrderBase {

    //日期字符串
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

    //订单产品信息
    List<OrderProductVo> orderProductVoList;

}

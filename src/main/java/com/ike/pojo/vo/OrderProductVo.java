package com.ike.pojo.vo;

import com.ike.pojo.OrderProduct;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Date: 2019-10-18 19:42
 * @Description:(描述)
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderProductVo extends OrderProduct {
    //产品名称
    private String productName;
    //产品类别名称
    private String className;
    //商品单价；
    private String salePrice;
    //成本
    private String saleCost;
    //商品名称：型号
    private String productMessages;
}

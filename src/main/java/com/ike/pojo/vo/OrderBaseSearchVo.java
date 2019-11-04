package com.ike.pojo.vo;

import com.ike.common.util.ServletBeanUtil;
import lombok.Data;
import org.apache.http.HttpRequest;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @Date: 2019-10-18 22:10
 * @Description:(描述) 用于做订单筛选的帮助类
 */
@Data
public class OrderBaseSearchVo {

    //订单id范围
    private Long orderBaseId;

    //订单成交总额范围
    private Double orderTotalMin;
    private Double orderTotalMax;

    //订单实际总额范围
    private Double orderActualTotalMin;
    private Double orderActualTotalMax;

    //订单总成本范围
    private Double orderCostMin;
    private Double orderCostMax;

    //订单回款总额
    private Double orderGetSumMin;
    private Double orderGetSumMax;

    //利润范围 （订单实际总额 - 订单总成本）
    private Double profitMin;
    private Double profitMax;

    //订单下单时间
    private Date beginCreateTime;
    private Date endCreateTime;

    //下单产品个数
    private Integer productCountMax;
    private Integer productCountMin;

    //顶单个数
    private Integer orderCountMax;
    private Integer orderCountMin;

    //订单联系人id (客户联系人id)
    private Long relationId;
    private String relationName;

    //客户ID
    private Long customerId;
    private String customerName;

    //订单负责人id (负责员工id)
    private Long employeeId;

    //产品名称
    private String productName;

    //订单状态
    private Byte orderState;

    //产品分类
    private String className;

    //排序列名
    private String sortName;

    //排序方式
    private String sortType;
    /**
     * 今天
     */
    private String isToday;
    /**
     * 昨天
     */
    private String yesterday;
    /**
     * 上月
     */
    private String preMouth;
    /**
     * 这个月
     */
    private String isMouth;

    public static OrderBaseSearchVo getProperties(HttpServletRequest request,
                                                  OrderBaseSearchVo searchVO) {
        ServletBeanUtil.populate(searchVO, request);
        //默认为id排序
        if (searchVO.getSortName() == null) {
            searchVO.setSortName("ob.id");
        }
        //默认为升序
        if (searchVO.getSortType() == null) {
            searchVO.setSortType("ASC");
        }
        return searchVO;
    }

}

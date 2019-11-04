package com.ike.pojo.vo;

import com.ike.common.util.ServletBeanUtil;
import lombok.Data;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @Description TODO
 * @Date 2019/10/20 22:11
 */
@Data
public class RefundSearchVo {
    //退款信息id
    private Long id;
    //创建人ID
    private Long createUserId;
    //负责人ID
    private Long employeeId;
    //创建时间
    private Date createTimeMax;
    private Date createTimeMin;
    //订单
    private Long orderBaseId;
    //退款时间
    private Date returnTimeMax;
    private Date returnTimeMin;
    //退款金额
    private Double amountMax;
    private Double amountMin;
    //退款方式
    private String returnMethod;
    //客户id
    private Long customerId;
    /**
     * 客户名字
     */
    private String customerName;
    /**
     * 客户联系人名字
     */
    private String relationName;
    /**
     * 排序的方法 asc或者desc 默认未asc
     */
    private String sort;

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



    public static RefundSearchVo getProperties(HttpServletRequest request, RefundSearchVo searchVO) {
        ServletBeanUtil.populate(searchVO, request);
        if (searchVO.getSort() == null || searchVO.getSort().trim().equals("")) {
            searchVO.setSort("asc");
        }
        return searchVO;
    }
}

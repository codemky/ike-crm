package com.ike.pojo.vo;

import com.ike.pojo.ReturnPlanDetail;

/**
 * @Description TODO
 * @Date 2019/10/18 22:48
 */
public class ReturnPlanVo extends ReturnPlanDetail {
    private Long orderBaseId;

    private String planDetail;

    public Long getOrderBaseId() {
        return orderBaseId;
    }

    public void setOrderBaseId(Long orderBaseId) {
        this.orderBaseId = orderBaseId;
    }

    public String getPlanDetail() {
        return planDetail;
    }

    public void setPlanDetail(String planDetail) {
        this.planDetail = planDetail;
    }
}

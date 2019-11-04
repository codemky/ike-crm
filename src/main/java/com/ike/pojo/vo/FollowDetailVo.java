package com.ike.pojo.vo;

import com.ike.pojo.Follow;

/**
 * ClassName FollowDetailVo
 * Description TODO
 *
 * @author mokuanyuan
 * @version 1.0
 * @date 2019/10/21 14:37
 **/
public class FollowDetailVo extends FollowVo {

    private Long planId;
    private Long arrangeId;

    public Long getPlanId() {
        return planId;
    }

    public void setPlanId(Long planId) {
        this.planId = planId;
    }

    public Long getArrangeId() {
        return arrangeId;
    }

    public void setArrangeId(Long arrangeId) {
        this.arrangeId = arrangeId;
    }
}

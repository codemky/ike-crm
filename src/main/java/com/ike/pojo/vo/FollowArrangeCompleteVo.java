package com.ike.pojo.vo;

import com.ike.pojo.FollowArrange;

import java.time.LocalDateTime;

/**
 * ClassName FollowArrangeCompleteVo
 * Description TODO
 *
 * @author mokuanyuan
 * @version 1.0
 * @date 2019/10/16 11:30
 **/
public class FollowArrangeCompleteVo extends FollowArrange {

    private String followWay;

    private LocalDateTime followTime;

    private String followDetail;

    private String followResult;


    public String getFollowWay() {
        return followWay;
    }

    public void setFollowWay(String followWay) {
        this.followWay = followWay;
    }

    public LocalDateTime getFollowTime() {
        return followTime;
    }

    public void setFollowTime(LocalDateTime followTime) {
        this.followTime = followTime;
    }

    public String getFollowDetail() {
        return followDetail;
    }

    public void setFollowDetail(String followDetail) {
        this.followDetail = followDetail;
    }

    public String getFollowResult() {
        return followResult;
    }

    public void setFollowResult(String followResult) {
        this.followResult = followResult;
    }
}

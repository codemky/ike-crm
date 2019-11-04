package com.ike.pojo;

import java.time.LocalDateTime;

public class CustomerStageLog {
    private Long id;

    private Long modifyUserId;

    private Long stageBefore;

    private Long stageAfter;

    private Long customerId;

    private LocalDateTime modifyTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getModifyUserId() {
        return modifyUserId;
    }

    public void setModifyUserId(Long modifyUserId) {
        this.modifyUserId = modifyUserId;
    }

    public Long getStageBefore() {
        return stageBefore;
    }

    public void setStageBefore(Long stageBefore) {
        this.stageBefore = stageBefore;
    }

    public Long getStageAfter() {
        return stageAfter;
    }

    public void setStageAfter(Long stageAfter) {
        this.stageAfter = stageAfter;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public LocalDateTime getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(LocalDateTime modifyTime) {
        this.modifyTime = modifyTime;
    }
}
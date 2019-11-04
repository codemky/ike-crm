package com.ike.pojo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

import java.time.LocalDateTime;

public class FollowPlan extends BaseRowModel {
    private Long id;

    private LocalDateTime planDate;
    @ExcelProperty(index = 0,value = "联系人id")
    private Long relationId;
    @ExcelProperty(index = 1,value = "员工id")
    private Long employeeId;

    private Long followId;
    @ExcelProperty(index = 2,value = "计划内容")
    private String planContent;

    private LocalDateTime createDate;

    private Byte iscomplete;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getPlanDate() {
        return planDate;
    }

    public void setPlanDate(LocalDateTime planDate) {
        this.planDate = planDate;
    }

    public Long getRelationId() {
        return relationId;
    }

    public void setRelationId(Long relationId) {
        this.relationId = relationId;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Long getFollowId() {
        return followId;
    }

    public void setFollowId(Long followId) {
        this.followId = followId;
    }

    public String getPlanContent() {
        return planContent;
    }

    public void setPlanContent(String planContent) {
        this.planContent = planContent == null ? null : planContent.trim();
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public Byte getIscomplete() {
        return iscomplete;
    }

    public void setIscomplete(Byte iscomplete) {
        this.iscomplete = iscomplete;
    }
}
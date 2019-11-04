package com.ike.pojo;

import java.time.LocalDateTime;

public class Complaint {
    private Long id;

    private Long relationId;

    private String complaintTheme;

    private LocalDateTime complaintTime;

    private String complaintType;

    private String complaintMethod;

    private String complaintContent;

    private String urgencyLevel;

    private String complaintPhone;

    private Long chargeId;

    private Long createUserId;

    private LocalDateTime createTime;

    private Long modifyUserId;

    private LocalDateTime modifyTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRelationId() {
        return relationId;
    }

    public void setRelationId(Long relationId) {
        this.relationId = relationId;
    }

    public String getComplaintTheme() {
        return complaintTheme;
    }

    public void setComplaintTheme(String complaintTheme) {
        this.complaintTheme = complaintTheme == null ? null : complaintTheme.trim();
    }

    public LocalDateTime getComplaintTime() {
        return complaintTime;
    }

    public void setComplaintTime(LocalDateTime complaintTime) {
        this.complaintTime = complaintTime;
    }

    public String getComplaintType() {
        return complaintType;
    }

    public void setComplaintType(String complaintType) {
        this.complaintType = complaintType == null ? null : complaintType.trim();
    }

    public String getComplaintMethod() {
        return complaintMethod;
    }

    public void setComplaintMethod(String complaintMethod) {
        this.complaintMethod = complaintMethod == null ? null : complaintMethod.trim();
    }

    public String getComplaintContent() {
        return complaintContent;
    }

    public void setComplaintContent(String complaintContent) {
        this.complaintContent = complaintContent == null ? null : complaintContent.trim();
    }

    public String getUrgencyLevel() {
        return urgencyLevel;
    }

    public void setUrgencyLevel(String urgencyLevel) {
        this.urgencyLevel = urgencyLevel == null ? null : urgencyLevel.trim();
    }

    public String getComplaintPhone() {
        return complaintPhone;
    }

    public void setComplaintPhone(String complaintPhone) {
        this.complaintPhone = complaintPhone == null ? null : complaintPhone.trim();
    }

    public Long getChargeId() {
        return chargeId;
    }

    public void setChargeId(Long chargeId) {
        this.chargeId = chargeId;
    }

    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public Long getModifyUserId() {
        return modifyUserId;
    }

    public void setModifyUserId(Long modifyUserId) {
        this.modifyUserId = modifyUserId;
    }

    public LocalDateTime getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(LocalDateTime modifyTime) {
        this.modifyTime = modifyTime;
    }
}
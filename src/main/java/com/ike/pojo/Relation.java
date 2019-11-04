package com.ike.pojo;

import java.time.LocalDateTime;

public class Relation {
    private Long id;

    private Long customerId;

    private String relationName;

    private Boolean relationSex;

    private String relationPosition;

    private String relationCall;

    private String relationPhone;

    private String relationEmail;

    private Byte relationPrimary;

    private String landlineNumber;

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

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getRelationName() {
        return relationName;
    }

    public void setRelationName(String relationName) {
        this.relationName = relationName == null ? null : relationName.trim();
    }

    public Boolean getRelationSex() {
        return relationSex;
    }

    public void setRelationSex(Boolean relationSex) {
        this.relationSex = relationSex;
    }

    public String getRelationPosition() {
        return relationPosition;
    }

    public void setRelationPosition(String relationPosition) {
        this.relationPosition = relationPosition == null ? null : relationPosition.trim();
    }

    public String getRelationCall() {
        return relationCall;
    }

    public void setRelationCall(String relationCall) {
        this.relationCall = relationCall == null ? null : relationCall.trim();
    }

    public String getRelationPhone() {
        return relationPhone;
    }

    public void setRelationPhone(String relationPhone) {
        this.relationPhone = relationPhone == null ? null : relationPhone.trim();
    }

    public String getRelationEmail() {
        return relationEmail;
    }

    public void setRelationEmail(String relationEmail) {
        this.relationEmail = relationEmail == null ? null : relationEmail.trim();
    }

    public Byte getRelationPrimary() {
        return relationPrimary;
    }

    public void setRelationPrimary(Byte relationPrimary) {
        this.relationPrimary = relationPrimary;
    }

    public String getLandlineNumber() {
        return landlineNumber;
    }

    public void setLandlineNumber(String landlineNumber) {
        this.landlineNumber = landlineNumber == null ? null : landlineNumber.trim();
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
package com.ike.pojo.vo;

import com.ike.pojo.Customer;
import com.ike.pojo.Relation;

/**
 * ClassName CustomerRelationVo
 * Description TODO
 *
 * @author mokuanyuan
 * @version 1.0
 * @date 2019/10/11 21:26
 **/
public class CustomerRelationVo extends Customer {

    private Long customerId;

    private String relationName;

    private Boolean relationSex;

    private String relationPosition;

    private String relationCall;

    private String relationPhone;

    private String relationEmail;

    private Byte relationPrimary;

    private String landlineNumber;


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
        this.relationName = relationName;
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
        this.relationPosition = relationPosition;
    }

    public String getRelationCall() {
        return relationCall;
    }

    public void setRelationCall(String relationCall) {
        this.relationCall = relationCall;
    }

    public String getRelationPhone() {
        return relationPhone;
    }

    public void setRelationPhone(String relationPhone) {
        this.relationPhone = relationPhone;
    }

    public String getRelationEmail() {
        return relationEmail;
    }

    public void setRelationEmail(String relationEmail) {
        this.relationEmail = relationEmail;
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
        this.landlineNumber = landlineNumber;
    }
}

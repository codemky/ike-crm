package com.ike.pojo.vo;

import com.ike.common.util.ServletBeanUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * ClassName FollowArrangeSearchVo
 * Description TODO
 *
 * @author mokuanyuan
 * @version 1.0
 * @date 2019/10/16 8:14
 **/
public class FollowArrangeSearchVo {

    private Long id;
    private Long arrangeId;
    private Long chargeId;
    private Long customerId;
    private Long relationId;
    private String isRead;
    private String notRead;
    private String isComplete;
    private String notComplete;
    private String notExecute;
    private String minDate;
    private String maxDate;
    private String arrangeName;
    private String chargeName;
    private String customerName;
    private String relationName;
    private String relationPhone;
    private String sortName;
    private String sortType;
    private String arrangeMyself;
    private String chargeMyself;

    public static FollowArrangeSearchVo getProperties(HttpServletRequest request, FollowArrangeSearchVo arrangeSearchVo) {

        ServletBeanUtil.populate(arrangeSearchVo, request);

        arrangeSearchVo.setArrangeName(ServletBeanUtil.getVague(arrangeSearchVo.getArrangeName()));
        arrangeSearchVo.setChargeName(ServletBeanUtil.getVague(arrangeSearchVo.getChargeName()));
        arrangeSearchVo.setCustomerName(ServletBeanUtil.getVague(arrangeSearchVo.getCustomerName()));
        arrangeSearchVo.setRelationName(ServletBeanUtil.getVague(arrangeSearchVo.getRelationName()));
        arrangeSearchVo.setRelationPhone(ServletBeanUtil.getVague(arrangeSearchVo.getRelationPhone()));

        //默认为id排序
        if (arrangeSearchVo.getSortName() == null) {
            arrangeSearchVo.setSortName("fa.id");
        }
        //默认为升序
        if (arrangeSearchVo.getSortType() == null) {
            arrangeSearchVo.setSortType("ASC");
        }

        return arrangeSearchVo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getArrangeMyself() {
        return arrangeMyself;
    }

    public void setArrangeMyself(String arrangeMyself) {
        this.arrangeMyself = arrangeMyself;
    }

    public String getChargeMyself() {
        return chargeMyself;
    }

    public void setChargeMyself(String chargeMyself) {
        this.chargeMyself = chargeMyself;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getRelationId() {
        return relationId;
    }

    public void setRelationId(Long relationId) {
        this.relationId = relationId;
    }

    public String getRelationPhone() {
        return relationPhone;
    }

    public void setRelationPhone(String relationPhone) {
        this.relationPhone = relationPhone;
    }

    public String getSortName() {
        return sortName;
    }

    public void setSortName(String sortName) {
        this.sortName = sortName;
    }

    public String getSortType() {
        return sortType;
    }

    public void setSortType(String sortType) {
        this.sortType = sortType;
    }

    public Long getArrangeId() {
        return arrangeId;
    }

    public void setArrangeId(Long arrangeId) {
        this.arrangeId = arrangeId;
    }

    public Long getChargeId() {
        return chargeId;
    }

    public void setChargeId(Long chargeId) {
        this.chargeId = chargeId;
    }

    public String getIsRead() {
        return isRead;
    }

    public void setIsRead(String isRead) {
        this.isRead = isRead;
    }

    public String getNotRead() {
        return notRead;
    }

    public void setNotRead(String notRead) {
        this.notRead = notRead;
    }

    public String getIsComplete() {
        return isComplete;
    }

    public void setIsComplete(String isComplete) {
        this.isComplete = isComplete;
    }

    public String getNotComplete() {
        return notComplete;
    }

    public void setNotComplete(String notComplete) {
        this.notComplete = notComplete;
    }

    public String getNotExecute() {
        return notExecute;
    }

    public void setNotExecute(String notExecute) {
        this.notExecute = notExecute;
    }

    public String getMinDate() {
        return minDate;
    }

    public void setMinDate(String minDate) {
        this.minDate = minDate;
    }

    public String getMaxDate() {
        return maxDate;
    }

    public void setMaxDate(String maxDate) {
        this.maxDate = maxDate;
    }

    public String getArrangeName() {
        return arrangeName;
    }

    public void setArrangeName(String arrangeName) {
        this.arrangeName = arrangeName;
    }

    public String getChargeName() {
        return chargeName;
    }

    public void setChargeName(String chargeName) {
        this.chargeName = chargeName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getRelationName() {
        return relationName;
    }

    public void setRelationName(String relationName) {
        this.relationName = relationName;
    }
}

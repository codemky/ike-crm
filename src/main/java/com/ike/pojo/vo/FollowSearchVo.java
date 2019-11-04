package com.ike.pojo.vo;

import com.ike.common.util.ServletBeanUtil;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * ClassName FollowSearchVo
 * Description TODO
 *
 * @author mokuanyuan
 * @version 1.0
 * @date 2019/10/15 15:46
 **/
public class FollowSearchVo {

    private Long id;

    private String followWay;

    private Long customerId;
    private Long relationId;
    private Long customerStageId;

    private String customerStatus;

    private List<Long> employeeIds;
    private String isMyself;

    private String customerName;
    private String relationName;
    private String relationPhone;

    private String minTime;

    private String maxTime;

    private String isToday;
    private String yesterday;
    private String isWeek;
    private String isMouth;

    private String sortName;
    private String sortType;

    public static FollowSearchVo getProperties(HttpServletRequest request, FollowSearchVo searchVo) {

        ServletBeanUtil.populate(searchVo, request);

        searchVo.setCustomerName(ServletBeanUtil.getVague(searchVo.getCustomerName()));
        searchVo.setRelationName(ServletBeanUtil.getVague(searchVo.getRelationName()));
        searchVo.setRelationPhone(ServletBeanUtil.getVague(searchVo.getRelationPhone()));
        searchVo.setFollowWay(ServletBeanUtil.getVague(searchVo.getFollowWay()));
        searchVo.setCustomerStatus(ServletBeanUtil.getVague(searchVo.getCustomerStatus()));
        searchVo.setEmployeeIds(ServletBeanUtil.populateEmployeeIds(request));
        if (searchVo.getEmployeeIds() != null && searchVo.getEmployeeIds().size() == 0) {
            searchVo.setEmployeeIds(null);
        }

        //默认为id排序
        if (searchVo.getSortName() == null) {
            searchVo.setSortName("f.id");
        }
        //默认为升序
        if (searchVo.getSortType() == null) {
            searchVo.setSortType("ASC");
        }

        return searchVo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIsMyself() {
        return isMyself;
    }

    public void setIsMyself(String isMyself) {
        this.isMyself = isMyself;
    }

    public Long getRelationId() {
        return relationId;
    }

    public void setRelationId(Long relationId) {
        this.relationId = relationId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
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

    public String getRelationPhone() {
        return relationPhone;
    }

    public void setRelationPhone(String relationPhone) {
        this.relationPhone = relationPhone;
    }

    public String getFollowWay() {
        return followWay;
    }

    public void setFollowWay(String followWay) {
        this.followWay = followWay;
    }

    public Long getCustomerStageId() {
        return customerStageId;
    }

    public void setCustomerStageId(Long customerStageId) {
        this.customerStageId = customerStageId;
    }

    public String getCustomerStatus() {
        return customerStatus;
    }

    public void setCustomerStatus(String customerStatus) {
        this.customerStatus = customerStatus;
    }

    public List<Long> getEmployeeIds() {
        return employeeIds;
    }

    public void setEmployeeIds(List<Long> employeeIds) {
        this.employeeIds = employeeIds;
    }

    public String getMinTime() {
        return minTime;
    }

    public void setMinTime(String minTime) {
        this.minTime = minTime;
    }

    public String getMaxTime() {
        return maxTime;
    }

    public void setMaxTime(String maxTime) {
        this.maxTime = maxTime;
    }

    public String getIsToday() {
        return isToday;
    }

    public void setIsToday(String isToday) {
        this.isToday = isToday;
    }

    public String getYesterday() {
        return yesterday;
    }

    public void setYesterday(String yesterday) {
        this.yesterday = yesterday;
    }

    public String getIsWeek() {
        return isWeek;
    }

    public void setIsWeek(String isWeek) {
        this.isWeek = isWeek;
    }

    public String getIsMouth() {
        return isMouth;
    }

    public void setIsMouth(String isMouth) {
        this.isMouth = isMouth;
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
}

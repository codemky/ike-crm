package com.ike.pojo.vo;

import com.ike.common.util.ServletBeanUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * ClassName FollowPlanSearchVo
 * Description TODO
 *
 * @author mokuanyuan
 * @version 1.0
 * @date 2019/10/15 17:46
 **/
public class FollowPlanSearchVo {

    private Long id;
    private List<Long> employeeIds;
    private String minDate;
    private String maxDate;
    private String isComplete;
    private String notComplete;
    private String notExecute;
    private String customerName;
    private String relationName;
    private String relationPhone;
    private String isToday;
    private String isTomorrow;
    private String isAfterTomorrow;
    private String isWeek;
    private String sortName;
    private String sortType;
    private Long customerId;
    private Long relationId;
    private String isMyself;


    public static FollowPlanSearchVo getProperties(HttpServletRequest request, FollowPlanSearchVo searchVo) {

        ServletBeanUtil.populate(searchVo, request);

        searchVo.setEmployeeIds(ServletBeanUtil.populateEmployeeIds(request));
        if (searchVo.getEmployeeIds() != null && searchVo.getEmployeeIds().size() == 0) {
            searchVo.setEmployeeIds(null);
        }
        searchVo.setCustomerName(ServletBeanUtil.getVague(searchVo.getCustomerName()));
        searchVo.setRelationName(ServletBeanUtil.getVague(searchVo.getRelationName()));
        searchVo.setRelationPhone(ServletBeanUtil.getVague(searchVo.getRelationPhone()));


        //默认为id排序
        if (searchVo.getSortName() == null) {
            searchVo.setSortName("fp.id");
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

    public String getNotExecute() {
        return notExecute;
    }

    public void setNotExecute(String notExecute) {
        this.notExecute = notExecute;
    }

    public String getIsToday() {
        return isToday;
    }

    public void setIsToday(String isToday) {
        this.isToday = isToday;
    }

    public String getIsTomorrow() {
        return isTomorrow;
    }

    public void setIsTomorrow(String isTomorrow) {
        this.isTomorrow = isTomorrow;
    }

    public String getIsAfterTomorrow() {
        return isAfterTomorrow;
    }

    public void setIsAfterTomorrow(String isAfterTomorrow) {
        this.isAfterTomorrow = isAfterTomorrow;
    }

    public String getIsWeek() {
        return isWeek;
    }

    public void setIsWeek(String isWeek) {
        this.isWeek = isWeek;
    }

    public List<Long> getEmployeeIds() {
        return employeeIds;
    }

    public void setEmployeeIds(List<Long> employeeIds) {
        this.employeeIds = employeeIds;
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

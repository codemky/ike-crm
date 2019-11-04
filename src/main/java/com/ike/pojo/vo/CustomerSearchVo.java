package com.ike.pojo.vo;

import com.ike.common.util.ServletBeanUtil;
import com.ike.pojo.Customer;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * ClassName CustomerSearchVo
 * Description TODO
 *
 * @author mokuanyuan
 * @version 1.0
 * @date 2019/10/12 14:35
 **/
public class CustomerSearchVo {
    private Long id;

    private String customerName;

    private Long employeeId;

    private Long customerStageId;

    private Long customerLevelId;

    private String customerState;

    private Long customerOriginId;

    private String customerIntroduce;

    private String customerAddress;

    private Long createUserId;

    private LocalDateTime createTime;

    private Long modifyUserId;

    private LocalDateTime modifyTime;

    private List<Long> ids;
    private Long departmentId;
    private List<Long> employeeIds;
    private Long orderMinCount;
    private Long orderMaxCount;
    private Double orderMinSum;
    private Double orderMaxSum;
    private Long complainMinCount;
    private Long complainMaxCount;
    private String sortName;
    private String sortType;
    private Boolean flag;
    private String relationPhone;
    private Long followMinCount;
    private Long followMaxCount;

    private Long differFollow;
    private String createMin;
    private String createMax;
    private String isToday;
    private String isFollowed;


    public static CustomerSearchVo getProperties(HttpServletRequest request, CustomerSearchVo searchVo) {

        ServletBeanUtil.populate(searchVo, request);

        String employIds = "employeeIds";

        if (request.getParameterValues(employIds) != null) {
        List<Long> employeeIds = Arrays.stream(request.getParameterValues(employIds)).filter(x-> !x.isEmpty())
                    .map(Long::parseLong).collect(Collectors.toList());
            if (employeeIds.size() > 0) {
                searchVo.setEmployeeIds(employeeIds);
            }
        }

//        if (searchVo.getEmployeeId() != null) {
//            searchVo.setEmployeeIds(Collections.singletonList(searchVo.getEmployeeId()));
//        }

        //默认为id排序
        if (searchVo.getSortName() == null) {
            searchVo.setSortName("c.id");
        }

        //默认为升序
        if (searchVo.getSortType() == null) {
            searchVo.setSortType("ASC");
        }

        if (searchVo.getCustomerName() != null) {
            searchVo.setCustomerName("%" + searchVo.getCustomerName() + "%");
        }
        if (searchVo.getCustomerAddress() != null) {
            searchVo.setCustomerAddress("%" + searchVo.getCustomerAddress() + "%");
        }
        if (searchVo.getRelationPhone() != null) {
            searchVo.setRelationPhone("%" + searchVo.getRelationPhone() + "%");
        }

        return searchVo;
    }

    @Override
    public String toString() {
        return "CustomerSearchVo{" +
                "ids=" + ids +
                ", departmentId=" + departmentId +
                ", employeeIds=" + employeeIds +
                ", orderMinCount=" + orderMinCount +
                ", orderMaxCount=" + orderMaxCount +
                ", orderMinSum=" + orderMinSum +
                ", orderMaxSum=" + orderMaxSum +
                ", complainMinCount=" + complainMinCount +
                ", complainMaxCount=" + complainMaxCount +
                ", sortName='" + sortName + '\'' +
                ", sortType='" + sortType + '\'' +
                ", flag=" + flag +
                '}';
    }

    public Long getFollowMinCount() {
        return followMinCount;
    }

    public void setFollowMinCount(Long followMinCount) {
        this.followMinCount = followMinCount;
    }

    public Long getFollowMaxCount() {
        return followMaxCount;
    }

    public void setFollowMaxCount(Long followMaxCount) {
        this.followMaxCount = followMaxCount;
    }

    public String getIsFollowed() {
        return isFollowed;
    }

    public void setIsFollowed(String isFollowed) {
        this.isFollowed = isFollowed;
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

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public List<Long> getEmployeeIds() {
        return employeeIds;
    }

    public void setEmployeeIds(List<Long> employeeIds) {
        this.employeeIds = employeeIds;
    }

    public Long getOrderMinCount() {
        return orderMinCount;
    }

    public void setOrderMinCount(Long orderMinCount) {
        this.orderMinCount = orderMinCount;
    }

    public Long getOrderMaxCount() {
        return orderMaxCount;
    }

    public void setOrderMaxCount(Long orderMaxCount) {
        this.orderMaxCount = orderMaxCount;
    }

    public Long getComplainMinCount() {
        return complainMinCount;
    }

    public void setComplainMinCount(Long complainMinCount) {
        this.complainMinCount = complainMinCount;
    }

    public Long getComplainMaxCount() {
        return complainMaxCount;
    }

    public void setComplainMaxCount(Long complainMaxCount) {
        this.complainMaxCount = complainMaxCount;
    }

    public Double getOrderMinSum() {
        return orderMinSum;
    }

    public void setOrderMinSum(Double orderMinSum) {
        this.orderMinSum = orderMinSum;
    }

    public Double getOrderMaxSum() {
        return orderMaxSum;
    }

    public void setOrderMaxSum(Double orderMaxSum) {
        this.orderMaxSum = orderMaxSum;
    }

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    public List<Long> getIds() {
        return ids;
    }

    public void setIds(List<Long> ids) {
        this.ids = ids;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Long getCustomerStageId() {
        return customerStageId;
    }

    public void setCustomerStageId(Long customerStageId) {
        this.customerStageId = customerStageId;
    }

    public Long getCustomerLevelId() {
        return customerLevelId;
    }

    public void setCustomerLevelId(Long customerLevelId) {
        this.customerLevelId = customerLevelId;
    }

    public String getCustomerState() {
        return customerState;
    }

    public void setCustomerState(String customerState) {
        this.customerState = customerState;
    }

    public Long getCustomerOriginId() {
        return customerOriginId;
    }

    public void setCustomerOriginId(Long customerOriginId) {
        this.customerOriginId = customerOriginId;
    }

    public String getCustomerIntroduce() {
        return customerIntroduce;
    }

    public void setCustomerIntroduce(String customerIntroduce) {
        this.customerIntroduce = customerIntroduce;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
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

    public Long getDifferFollow() {
        return differFollow;
    }

    public void setDifferFollow(Long differFollow) {
        this.differFollow = differFollow;
    }

    public String getCreateMin() {
        return createMin;
    }

    public void setCreateMin(String createMin) {
        this.createMin = createMin;
    }

    public String getCreateMax() {
        return createMax;
    }

    public void setCreateMax(String createMax) {
        this.createMax = createMax;
    }

    public String getIsToday() {
        return isToday;
    }

    public void setIsToday(String isToday) {
        this.isToday = isToday;
    }
}

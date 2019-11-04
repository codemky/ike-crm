package com.ike.pojo.vo;

import com.ike.common.util.ServletBeanUtil;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

/**
 * 产品销量统计条件类
 * @Author wuchuxin
 * @Date 2019/10/17 11:28
 * @Version 1.0
 */
public class StaProSearchVO {
    private Integer year;
    private Integer month;
    private Integer day;
    private String beginDate;
    private String endDate;
    private Long preId;

    public static StaProSearchVO getProperties(HttpServletRequest request, StaProSearchVO searchVO) {
        ServletBeanUtil.populate(searchVO, request);

        return searchVO;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Long getPreId() {
        return preId;
    }

    public void setPreId(Long preId) {
        this.preId = preId;
    }
}

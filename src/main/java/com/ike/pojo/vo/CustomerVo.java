package com.ike.pojo.vo;

import com.ike.pojo.Customer;

/**
 * ClassName CustomerVo
 * Description TODO
 *
 * @author mokuanyuan
 * @version 1.0
 * @date 2019/10/11 16:53
 **/
public class CustomerVo extends Customer {

    private String stageName;
    private String originName;
    private String levelName;
    private String employeeName;

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getStageName() {
        return stageName;
    }

    public void setStageName(String stageName) {
        this.stageName = stageName;
    }

    public String getOriginName() {
        return originName;
    }

    public void setOriginName(String originName) {
        this.originName = originName;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }
}

package com.ike.pojo.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.ike.pojo.User;

public class UserVo extends User {

    @ExcelProperty(index = 2, value = "性别")
    private String genderStr;

    @ExcelProperty(index = 3, value = "所属部门")
    private String departmentName;

    @ExcelProperty(index = 4, value = "职位")
    private String positionName;

    public String getGenderStr() {
        return genderStr;
    }

    public void setGenderStr(String genderStr) {
        this.genderStr = genderStr;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    @Override
    public String toString() {
        return "UserVo{" +
                       "genderStr='" + genderStr + '\'' +
                       ", departmentName='" + departmentName + '\'' +
                       ", positionName='" + positionName + '\'' +
                       '}';
    }
}

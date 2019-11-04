package com.ike.pojo.vo;

import com.ike.pojo.CustomerStageLog;

/**
 * @author LonelySeven
 * @version 1.0
 * @date 2019/10/9 20:38
 **/
public class CustomerStageLogVo extends CustomerStageLog {

    private String modifyName;

    private String stageBeforeName;

    private String stageAfterName;

    public String getModifyName() {
        return modifyName;
    }

    public void setModifyName(String modifyName) {
        this.modifyName = modifyName;
    }

    public String getStageBeforeName() {
        return stageBeforeName;
    }

    public void setStageBeforeName(String stageBeforeName) {
        this.stageBeforeName = stageBeforeName;
    }

    public String getStageAfterName() {
        return stageAfterName;
    }

    public void setStageAfterName(String stageAfterName) {
        this.stageAfterName = stageAfterName;
    }
}

package com.ike.pojo.vo;

import com.ike.pojo.Complaint;
import lombok.Data;

@Data
public class ComplaintVo extends Complaint {

    private String relationName;

    private String chargerName;

    private String creatorName;

    private String modifierName;

    private String customerName;

    //默认未处理为0，已完成1，未能完成2
    private int handleStatus;

}

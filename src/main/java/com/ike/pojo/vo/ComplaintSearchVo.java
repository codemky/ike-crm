package com.ike.pojo.vo;

import com.ike.pojo.Complaint;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ComplaintSearchVo{

    private String customerName;

    private String complaintType;

    private String complaintMethod;

    private String urgencyLevel;

    private String relationName;

    private String chargerName;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

}

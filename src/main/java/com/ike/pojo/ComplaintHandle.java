package com.ike.pojo;

import java.time.LocalDateTime;

public class ComplaintHandle {
    private Long id;

    private Long complaintId;

    private Long employeeId;

    private String handleDetail;

    private LocalDateTime handleTime;

    private Byte handleResult;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getComplaintId() {
        return complaintId;
    }

    public void setComplaintId(Long complaintId) {
        this.complaintId = complaintId;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getHandleDetail() {
        return handleDetail;
    }

    public void setHandleDetail(String handleDetail) {
        this.handleDetail = handleDetail == null ? null : handleDetail.trim();
    }

    public LocalDateTime getHandleTime() {
        return handleTime;
    }

    public void setHandleTime(LocalDateTime handleTime) {
        this.handleTime = handleTime;
    }

    public Byte getHandleResult() {
        return handleResult;
    }

    public void setHandleResult(Byte handleResult) {
        this.handleResult = handleResult;
    }
}
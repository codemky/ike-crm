package com.ike.service;


import com.ike.pojo.ComplaintHandle;
import com.ike.pojo.vo.ComplaintHandleVo;

import java.util.List;

public interface ComplaintHandleService {

    /**
     * 根据投诉记录id查询投诉处理记录
     */
    List<ComplaintHandleVo> selAllByComplaintId(Long complaintId);

    /**
     * 处理一条投诉记录-新增
     */
    int handleComplaint(ComplaintHandle complaintHandle);


    /**
     * 根据用户id删除与其所有投诉处理相关的记录
     */
    int deleteAboutUserId(Long userId);

    /**
     * 根据投诉记录id删除关联的投诉处理记录
     */
    int deleteByIds(List<Long> ids);

}

package com.ike.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ike.common.result.Result;
import com.ike.pojo.Complaint;
import com.ike.pojo.Message;
import com.ike.pojo.vo.ComplaintSearchVo;
import com.ike.pojo.vo.ComplaintVo;
import com.ike.pojo.vo.MessageVo;

import java.util.List;

public interface ComplaintService {

    /**
     * 查询全部投诉记录
     */
    IPage<ComplaintVo> selAll(Page page);

    /**
     * 查询未处理投诉记录
     */
    IPage<ComplaintVo> selUnHandle(Page page);

    /**
     * 查询已处理投诉记录
     */
    IPage<ComplaintVo> selIsHandled(Page page);

    /**
     * 查询全部投诉记录
     */
    IPage<ComplaintVo> selHandleByCurUser(Page page, Long curUserId);


    /**
     * 删除指定id的投诉记录
     */
    int deleteById(Long id);

    /**
     * 批量删除指定id的投诉记录
     */
    int batchDelete(List<Long> ids);

    /**
     * 新建投诉记录
     */
    int insertComplaint(Complaint complaint);

    /**
     * 更新投诉记录
     */
    int updateComplaint(Complaint complaint);


    /**
     * 高级筛选
     */
    IPage<ComplaintVo> advanceSearch(Page<ComplaintVo> page, ComplaintSearchVo complaintSearchVo);

    /**
     * 根据用户id删除与其所有投诉相关的记录
     */
    int deleteAboutUserId(Long userId);

    /**
     * 根据客户联系人删除投诉记录数&关联的投诉处理记录
     */
    int deleteByCustomerId(Long customerId);

    /**
     * 根据客户联系人id查询关联的投诉记录总数
     */
    int selCountByRelationId(Long relationId);

    /**
     * 将before员工负责的投诉记录&投诉处理记录 转交 给after员工
     */
    int transferComplaint(Long before, Long after);
}

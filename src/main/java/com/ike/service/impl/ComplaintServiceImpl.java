package com.ike.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ike.common.constans.CodeMsg;
import com.ike.common.exception.IKEException;
import com.ike.mapper.ComplaintMapper;
import com.ike.mapper.RelationMapper;
import com.ike.mapper.ext.ComplaintExtMapper;
import com.ike.pojo.*;
import com.ike.pojo.vo.ComplaintSearchVo;
import com.ike.pojo.vo.ComplaintVo;
import com.ike.service.ComplaintHandleService;
import com.ike.service.ComplaintService;
import com.sun.tools.javac.jvm.Code;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ComplaintServiceImpl implements ComplaintService {

    @Autowired
    private ComplaintMapper complaintMapper;

    @Autowired
    private ComplaintExtMapper extMapper;

    @Autowired
    private ComplaintHandleService handleService;

    @Autowired
    private RelationMapper relationMapper;

    @Override
    public IPage<ComplaintVo> selAll(Page page) {
        return extMapper.selAll(page);
    }

    @Override
    public IPage<ComplaintVo> selUnHandle(Page page) {
        return extMapper.selUnHandle(page);
    }

    @Override
    public IPage<ComplaintVo> selIsHandled(Page page) {
        return extMapper.selIsHandled(page);
    }

    @Override
    public IPage<ComplaintVo> selHandleByCurUser(Page page, Long curUserId) {
        return extMapper.selHandleByCurUser(page, curUserId);
    }

    @Override
    public int deleteById(Long id) {
        ComplaintExample example = new ComplaintExample();
        example.createCriteria().andIdEqualTo(id);
        return complaintMapper.deleteByExample(example);
    }

    @Transactional
    @Override
    public int batchDelete(List<Long> ids) {
        int count = handleService.deleteByIds(ids);
        if ( count < 0 ) {
            throw new IKEException(CodeMsg.COMPLAINT_HANDLE_DELETE_ERROR);
        }
//        int i = 1 / 0;  异常回滚测试
        ComplaintExample example = new ComplaintExample();
        example.createCriteria().andIdIn(ids);
        return complaintMapper.deleteByExample(example);
    }

    @Override
    public int insertComplaint(Complaint complaint) {
        return complaintMapper.insert(complaint);
    }

    @Override
    public int updateComplaint(Complaint complaint) {
        return complaintMapper.updateByExampleSelective(complaint, null);
    }

    @Override
    public IPage<ComplaintVo> advanceSearch(Page<ComplaintVo> page, ComplaintSearchVo complaintSearchVo) {
        return extMapper.advanceSearch(page, complaintSearchVo);
    }

    @Transactional
    @Override
    public int deleteAboutUserId(Long userId) {
        int i = handleService.deleteAboutUserId(userId);
        if ( i < 0 ) {
            throw new IKEException(CodeMsg.ERROR);
        }
        ComplaintExample example = new ComplaintExample();
        example.or().andChargeIdEqualTo(userId);
        example.or().andCreateUserIdEqualTo(userId);
        example.or().andModifyUserIdEqualTo(userId);
        return complaintMapper.deleteByExample(example);
    }

    @Transactional
    @Override
    public int deleteByCustomerId(Long customerId) {
        // 1. 先删除与客户相关的投诉记录->所相关的投诉处理记录
        // 2. 先删除与客户相关的投诉记录 所相关的投诉处理记录
        List<Long> ids = extMapper.selByCustomerId(customerId);
        if (ids.size() > 0) {
            int count = handleService.deleteByIds(ids);
            if (count < 0) {
                throw new IKEException(CodeMsg.COMPLAINT_HANDLE_DELETE_ERROR);
            }
            ComplaintExample example = new ComplaintExample();
            example.createCriteria().andIdIn(ids);
            return complaintMapper.deleteByExample(example);
        } else {
            return 0;
        }
    }

    @Override
    public int selCountByRelationId(Long relationId) {
        ComplaintExample example = new ComplaintExample();
        example.createCriteria().andRelationIdEqualTo(relationId);
        return complaintMapper.countByExample(example);
    }

    @Override
    public int transferComplaint(Long before, Long after) {
        // 转交投诉记录
        ComplaintExample complaintExample = new ComplaintExample();
        complaintExample.createCriteria().andChargeIdEqualTo(before);
        Complaint complaint = new Complaint();
        complaint.setChargeId(after);
        return complaintMapper.updateByExampleSelective(complaint, complaintExample);
    }
}

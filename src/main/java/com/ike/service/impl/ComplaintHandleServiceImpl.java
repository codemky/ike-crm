package com.ike.service.impl;

import com.ike.mapper.ComplaintHandleMapper;
import com.ike.mapper.ComplaintMapper;
import com.ike.mapper.ext.ComplaintExtMapper;
import com.ike.mapper.ext.ComplaintHandleExtMapper;
import com.ike.pojo.ComplaintExample;
import com.ike.pojo.ComplaintHandle;
import com.ike.pojo.ComplaintHandleExample;
import com.ike.pojo.vo.ComplaintHandleVo;
import com.ike.service.ComplaintHandleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComplaintHandleServiceImpl implements ComplaintHandleService {

    @Autowired
    private ComplaintHandleMapper mapper;

    @Autowired
    private ComplaintHandleExtMapper extMapper;

    @Override
    public List<ComplaintHandleVo> selAllByComplaintId(Long complaintId) {
        return extMapper.selAllByComplaintId(complaintId);
    }

    @Override
    public int handleComplaint(ComplaintHandle complaintHandle) {
        return mapper.insert(complaintHandle);
    }

    @Override
    public int deleteAboutUserId(Long userId) {
        ComplaintHandleExample example = new ComplaintHandleExample();
        example.createCriteria().andEmployeeIdEqualTo(userId);
        return mapper.deleteByExample(example);
    }

    @Override
    public int deleteByIds(List<Long> ids) {
        ComplaintHandleExample example = new ComplaintHandleExample();
        example.createCriteria().andComplaintIdIn(ids);
        return mapper.deleteByExample(example);
    }
}

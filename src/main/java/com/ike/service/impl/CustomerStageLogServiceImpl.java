package com.ike.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ike.mapper.CustomerStageLogMapper;
import com.ike.mapper.ext.CustomerStageLogExtMapper;
import com.ike.pojo.CustomerStageLog;
import com.ike.service.CustomerStageLogService;
import com.ike.pojo.vo.CustomerStageLogVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * ClassName CustomerStageLogServiceImpl
 * Description TODO
 *
 * @author LonelySeven
 * @version 1.0
 * @date 2019/10/9 21:08
 **/
@Service
public class CustomerStageLogServiceImpl implements CustomerStageLogService {

    @Autowired
    private CustomerStageLogExtMapper stageLogExtMapper;
    @Autowired
    private CustomerStageLogMapper logMapper;


    @Override
    public IPage<CustomerStageLogVo> selectStageLogByCustomerId(Page page, Long cid) {
        return stageLogExtMapper.selectByCustomerId(page, cid);
    }

    @Override
    public int createForUpdate(CustomerStageLog stageLog, Long beforeId,Long afterId, Long eid, Long cid ) {
        stageLog.setCustomerId(cid);
        stageLog.setModifyUserId(eid);
        stageLog.setModifyTime(LocalDateTime.now());
        stageLog.setStageBefore(beforeId);
        stageLog.setStageAfter(afterId);

        return logMapper.insertSelective(stageLog);
    }

}

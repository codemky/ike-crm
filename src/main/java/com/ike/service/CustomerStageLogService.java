package com.ike.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ike.pojo.CustomerStageLog;
import com.ike.pojo.vo.CustomerStageLogVo;

import java.util.List;

/**
 * @author LonelySeven
 * @version 1.0
 * @date 2019/10/9 21:02
 **/
public interface CustomerStageLogService {

    IPage<CustomerStageLogVo> selectStageLogByCustomerId(Page page, Long cid);

    /**
     * Author: mokuanyuan
     * @param stageLog 空内容的阶段修改日志对象
     * @param beforeId 更新前的阶段id
     * @param afterId 更新后的阶段id
     * @param eid 员工id
     * @param cid 客户id
     * @return int 更新条目数
     * @apiNote: 更新客户阶段信息后生成的日志信息
     * @since:  2019/10/11 15:09
     */
    int createForUpdate(CustomerStageLog stageLog, Long beforeId,Long afterId, Long eid, Long cid);

}

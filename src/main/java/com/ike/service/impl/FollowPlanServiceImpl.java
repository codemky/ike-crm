package com.ike.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ike.mapper.FollowPlanMapper;
import com.ike.mapper.ext.FollowPlanExtMapper;
import com.ike.pojo.FollowPlan;
import com.ike.pojo.FollowPlanExample;
import com.ike.pojo.vo.FollowPlanSearchVo;
import com.ike.pojo.vo.FollowPlanVo;
import com.ike.pojo.vo.FollowSearchVo;
import com.ike.pojo.vo.FollowVo;
import com.ike.service.FollowPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClassName FollowPlanServiceImpl
 * Description TODO
 *
 * @author mokuanyuan
 * @version 1.0
 * @date 2019/10/15 19:11
 **/
@Service
public class FollowPlanServiceImpl implements FollowPlanService {

    @Autowired
    private FollowPlanMapper followPlanMapper;
    @Autowired
    private FollowPlanExtMapper followPlanExtMapper;

    @Override
    public IPage<FollowPlanVo> selectByCriteria(Page page, FollowPlanSearchVo criteria) {

        page.setOptimizeCountSql(false);
        return followPlanExtMapper.selectByCriteria(page, criteria);
    }


    @Override
    public int create(FollowPlan followPlan) {
        return followPlanMapper.insertSelective(followPlan);
    }

    @Override
    public int update(FollowPlan followPlan) {
        return followPlanMapper.updateByPrimaryKeySelective(followPlan);
    }

    @Override
    public int delete(Long id) {
        return followPlanMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int deleteByIds(List<Long> ids) {
        if (ids.size() > 0) {
            FollowPlanExample followPlanExample = new FollowPlanExample();
            followPlanExample.createCriteria().andIdIn(ids);
            return followPlanMapper.deleteByExample(followPlanExample);
        }
        return 0;
    }

    @Override
    public FollowPlan detail(Long id) {
        return followPlanMapper.selectByPrimaryKey(id);
    }

    @Override
    public FollowPlanVo selectDetail(Long id) {
        FollowPlanSearchVo followPlanSearchVo = new FollowPlanSearchVo();
        followPlanSearchVo.setId(id);
        followPlanSearchVo.setSortName("fp.id");
        followPlanSearchVo.setSortType("asc");
        List<FollowPlanVo> records = selectByCriteria(new Page(1, 1), followPlanSearchVo).getRecords();
        if (records.size() > 0) {
            return records.get(0);
        }
        return null;
    }

    @Override
    public int countByRelationIds(List<Long> ids) {
        if (ids.size() > 0) {
            FollowPlanExample followPlanExample = new FollowPlanExample();
            followPlanExample.createCriteria().andRelationIdIn(ids);
            return followPlanMapper.countByExample(followPlanExample);
        }
        return 0;
    }

    @Override
    public int deleteByRelationIds(List<Long> ids) {
        if (ids.size() > 0) {
            FollowPlanExample followPlanExample = new FollowPlanExample();
            followPlanExample.createCriteria().andRelationIdIn(ids);
            return followPlanMapper.deleteByExample(followPlanExample);
        }
        return 0;
    }

    @Override
    public int transferCustomer(Long before, Long after) {
        return followPlanExtMapper.updateForTransferPlan(before, after);
    }
}

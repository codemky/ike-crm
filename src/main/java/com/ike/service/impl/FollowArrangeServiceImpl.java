package com.ike.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ike.mapper.FollowArrangeMapper;
import com.ike.mapper.ext.FollowArrangeExtMapper;
import com.ike.mapper.ext.FollowPlanExtMapper;
import com.ike.pojo.FollowArrange;
import com.ike.pojo.FollowArrangeExample;
import com.ike.pojo.vo.FollowArrangeSearchVo;
import com.ike.pojo.vo.FollowArrangeVo;
import com.ike.service.FollowArrangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClassName FollowArrangeServiceImpl
 * Description TODO
 *
 * @author mokuanyuan
 * @version 1.0
 * @date 2019/10/16 9:11
 **/
@Service
public class FollowArrangeServiceImpl implements FollowArrangeService {

    @Autowired
    private FollowArrangeMapper arrangeMapper;
    @Autowired
    private FollowArrangeExtMapper arrangeExtMapper;
    @Autowired
    private FollowPlanExtMapper planExtMapper;

    @Override
    public IPage<FollowArrangeVo> selectByCriteria(Page page, FollowArrangeSearchVo criteria) {

        page.setOptimizeCountSql(false);
        return arrangeExtMapper.selectByCriteria(page, criteria);
    }


    @Override
    public int create(FollowArrange followArrange) {
        return arrangeMapper.insertSelective(followArrange);
    }

    @Override
    public int update(FollowArrange followArrange) {
        return arrangeMapper.updateByPrimaryKeySelective(followArrange);
    }

    @Override
    public int delete(Long id) {
        return arrangeMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int deleteByIds(List<Long> ids) {
        if (ids.size() > 0) {
            FollowArrangeExample followArrangeExample = new FollowArrangeExample();
            followArrangeExample.createCriteria().andIdIn(ids);
            return arrangeMapper.deleteByExample(followArrangeExample);
        }
        return 0;
    }

    @Override
    public FollowArrange detail(Long id) {
        return arrangeMapper.selectByPrimaryKey(id);
    }

    @Override
    public FollowArrangeVo selectDetail(Long id) {
        FollowArrangeSearchVo arrangeSearchVo = new FollowArrangeSearchVo();
        arrangeSearchVo.setId(id);
        arrangeSearchVo.setSortName("fa.id");
        arrangeSearchVo.setSortType("asc");
        List<FollowArrangeVo> records = selectByCriteria(new Page(1, 1), arrangeSearchVo).getRecords();
        if (records.size() > 0) {
            return records.get(0);
        }
        return null;
    }

    @Override
    public int countByRelationIds(List<Long> ids) {
        if (ids.size() > 0) {
            FollowArrangeExample followArrangeExample = new FollowArrangeExample();
            followArrangeExample.createCriteria().andRelationIdIn(ids);
            return arrangeMapper.countByExample(followArrangeExample);
        }
        return 0;
    }

    @Override
    public int deleteByRelationIds(List<Long> ids) {
        if (ids.size() > 0) {
            FollowArrangeExample followPlanExample = new FollowArrangeExample();
            followPlanExample.createCriteria().andRelationIdIn(ids);
            return arrangeMapper.deleteByExample(followPlanExample);
        }
        return 0;
    }

    @Override
    public int transferCustomer(Long before, Long after) {
        int arrange = planExtMapper.updateForTransferArrange(before, after);
        int charge = planExtMapper.updateForTransferCharge(before, after);

        return arrange + charge;
    }
}

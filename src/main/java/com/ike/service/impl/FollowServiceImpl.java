package com.ike.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ike.mapper.FollowArrangeMapper;
import com.ike.mapper.FollowMapper;
import com.ike.mapper.FollowPlanMapper;
import com.ike.mapper.ext.FollowArrangeExtMapper;
import com.ike.mapper.ext.FollowExtMapper;
import com.ike.mapper.ext.FollowPlanExtMapper;
import com.ike.pojo.*;
import com.ike.pojo.vo.FollowDetailVo;
import com.ike.pojo.vo.FollowSearchVo;
import com.ike.pojo.vo.FollowVo;
import com.ike.service.FollowService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * ClassName FollowServiceImpl
 * Description TODO
 *
 * @author mokuanyuan
 * @version 1.0
 * @date 2019/10/15 11:31
 **/
@Service
public class FollowServiceImpl implements FollowService {

    @Autowired
    private FollowMapper followMapper;
    @Autowired
    private FollowExtMapper followExtMapper;
    @Autowired
    private FollowPlanMapper planMapper;
    @Autowired
    private FollowArrangeMapper arrangeMapper;
    @Autowired
    private FollowPlanExtMapper planExtMapper;
    @Autowired
    private FollowArrangeExtMapper arrangeExtMapper;

    @Override
    public IPage<FollowVo> selectByCriteria(Page page, FollowSearchVo searchVo) {

        page.setOptimizeCountSql(false);

        return followExtMapper.selectByCriteria(page, searchVo);
    }

    @Override
    public int create(Follow follow) {

        return followMapper.insertSelective(follow);
    }

    @Override
    public int update(Follow follow) {
        return followMapper.updateByPrimaryKeySelective(follow);
    }

    @Override
    public int delete(Long id) {
        return followMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int deleteArrays(List<Long> ids) {
        FollowExample followExample = new FollowExample();
        if (ids.size() > 0) {
            followExample.createCriteria().andIdIn(ids);
        }
        return followMapper.deleteByExample(followExample);
    }

    @Override
    public Follow detail(Long id) {
        return followMapper.selectByPrimaryKey(id);
    }

    @Override
    public FollowDetailVo selectDetailVo(Long id) {
        FollowDetailVo followDetailVo = new FollowDetailVo();
        FollowSearchVo criteria = new FollowSearchVo();
        criteria.setId(id);
        criteria.setSortName("f.id");
        criteria.setSortType("asc");
        List<FollowVo> records = selectByCriteria(new Page(1, 1), criteria).getRecords();
        if (records.size() > 0) {
            BeanUtils.copyProperties(records.get(0), followDetailVo);

            //找跟进计划记录
            FollowPlanExample followPlanExample = new FollowPlanExample();
            followPlanExample.createCriteria().andFollowIdEqualTo(id);
            List<FollowPlan> followPlans = planMapper.selectByExample(followPlanExample);
            if (followPlans.size() > 0) {
                followDetailVo.setPlanId(followPlans.get(0).getId());
            }

            //找跟进任务记录
            FollowArrangeExample followArrangeExample = new FollowArrangeExample();
            followArrangeExample.createCriteria().andFollowIdEqualTo(id);
            List<FollowArrange> followArranges = arrangeMapper.selectByExample(followArrangeExample);
            if (followArranges.size() > 0) {
                followDetailVo.setArrangeId(followArranges.get(0).getId());
            }

            return followDetailVo;
        }

        return null;
    }

    @Override
    public int countByRelationId(Long id) {
        FollowExample followExample = new FollowExample();
        followExample.createCriteria().andRelationIdEqualTo(id);

        return followMapper.countByExample(followExample);
    }

    @Override
    public int deleteByCustomerId(Long id) {
        FollowExample followExample = new FollowExample();
        followExample.createCriteria().andCustomerIdEqualTo(id);

        return followMapper.deleteByExample(followExample);
    }

    @Override
    public int transferCustomer(Long before, Long after) {
        Follow follow = new Follow();
        follow.setEmployeeId(after);

        FollowExample followExample = new FollowExample();
        followExample.createCriteria().andEmployeeIdEqualTo(before);

        return followMapper.updateByExampleSelective(follow, followExample);
    }

}

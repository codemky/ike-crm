package com.ike.service.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ike.mapper.OrderBaseMapper;
import com.ike.mapper.ReturnPlanMapper;
import com.ike.mapper.UserMapper;
import com.ike.mapper.ext.ReturnPlanExtMapper;
import com.ike.pojo.*;
import com.ike.service.ReturnPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReturnPlanServiceImpl implements ReturnPlanService {

    @Autowired
    private ReturnPlanMapper returnPlanMapper;

    @Autowired
    private OrderBaseMapper orderBaseMapper;

    @Autowired
    private ReturnPlanExtMapper returnPlanExtMapper;

    @Override
    public List<ReturnPlan> selectAll() {
        return returnPlanMapper.selectByExample(null);
    }

    @Override
    public int insert(ReturnPlan returnPlan) {
        return returnPlanMapper.insert(returnPlan);
    }

    @Override
    public int update(ReturnPlan returnPlan) {
        return returnPlanMapper.updateByPrimaryKeySelective(returnPlan);
    }

    @Override
    public int deleteById(Long id) {
        return returnPlanMapper.deleteByPrimaryKey(id);
    }

    @Override
    public ReturnPlan selectById(Long id) {
        return returnPlanMapper.selectByPrimaryKey(id);
    }

    @Override
    public int deleteByKeyId(Long id) {
        ReturnPlan returnPlans = returnPlanMapper.selectByPrimaryKey(id);
        long rp_id = returnPlans.getId();
        OrderBaseExample example = new OrderBaseExample();
        OrderBaseExample.Criteria criteria = example.createCriteria();
        criteria.andReturnPlanIdEqualTo(rp_id);
        List<OrderBase> o_list = orderBaseMapper.selectByExample(example);
        if (o_list.size() == 0) {
            returnPlanMapper.deleteByPrimaryKey(id);
            return 1;
        }else{
            return 0;
        }
    }

    @Override
    public ReturnPlan findByOrderId(Long id) {
        return returnPlanExtMapper.selectByOrderId(id);
    }

    @Override
    public int updateReturnPlanId(Long old_plan_id, Long new_plan_id) {
        OrderBaseExample example = new OrderBaseExample();
        OrderBaseExample.Criteria criteria = example.createCriteria();
        criteria.andReturnPlanIdEqualTo(old_plan_id);
        OrderBase orderBase = new OrderBase();
        orderBase.setReturnPlanId(new_plan_id);
        return orderBaseMapper.updateByExampleSelective(orderBase, example);
    }
}

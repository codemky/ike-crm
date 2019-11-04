package com.ike.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ike.mapper.OrderBaseMapper;
import com.ike.mapper.ReturnPlanDetailMapper;
import com.ike.mapper.ext.ReturnPlanExtMapper;
import com.ike.pojo.OrderBase;
import com.ike.pojo.ReturnPlanDetail;
import com.ike.pojo.ReturnPlanDetailExample;
import com.ike.pojo.vo.ReturnPlanVo;
import com.ike.service.ReturnPlanDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReturnPlanDetailServiceImpl implements ReturnPlanDetailService {

    @Autowired
    private ReturnPlanDetailMapper returnPlanDetailMapper;


    @Autowired
    private ReturnPlanExtMapper returnPlanExtMapper;

    @Autowired
    private OrderBaseMapper orderBaseMapper;

    @Override
    public List<ReturnPlanDetail> selectAll() {
        return returnPlanDetailMapper.selectByExample(null);
    }

    @Override
    public int insert(ReturnPlanDetail returnPlanDetail) {
        return returnPlanDetailMapper.insertSelective(returnPlanDetail);
    }

    @Override
    public int update(ReturnPlanDetail returnPlanDetail) {
        return returnPlanDetailMapper.updateByPrimaryKeySelective(returnPlanDetail);
    }

    @Override
    public int deleteById(Long id) {
        return returnPlanDetailMapper.deleteByPrimaryKey(id);
    }

    @Override
    public ReturnPlanDetail selectById(Long id) {
        return returnPlanDetailMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<ReturnPlanVo> listAll() {
        return returnPlanExtMapper.selectListAll();
    }

    @Override
    public List<ReturnPlanVo> selectListByPlanId(Long plan_id) {
        return returnPlanExtMapper.selectListByPlanId(plan_id);
    }

    @Override
    public List<ReturnPlanDetail> selectByReturnPlanIdAndReturnNumber(Long return_plan_id, Integer returnNumber) {
        ReturnPlanDetailExample example = new ReturnPlanDetailExample();
        ReturnPlanDetailExample.Criteria criteria = example.createCriteria();
        criteria.andReturnPlanIdEqualTo(return_plan_id);
        criteria.andReturnNumberEqualTo(returnNumber);
        return returnPlanDetailMapper.selectByExample(example);
    }

    @Override
    public int countId(Long returnPlanId) {
        QueryWrapper<ReturnPlanDetail> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("return_plan_id",returnPlanId);
        return returnPlanDetailMapper.selectCount(queryWrapper);
    }

    @Override
    public int deleteByKeyId(Long id) {
        QueryWrapper<ReturnPlanDetail> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("return_plan_id").eq("id",id);
        List<ReturnPlanDetail> r_list = returnPlanDetailMapper.selectList(queryWrapper);
        QueryWrapper<OrderBase> oqueryWrapper = new QueryWrapper<>();
        queryWrapper.select("return_plan_id");
        List<OrderBase> o_list = orderBaseMapper.selectList(oqueryWrapper);
        int falg = 0;
        for(OrderBase o : o_list){
            for(ReturnPlanDetail r : r_list){
                if (o.getReturnPlanId() == r.getReturnPlanId()){
                    falg = 1;
                }
            }
        }
        if (falg == 0) {
            returnPlanDetailMapper.deleteByPrimaryKey(id);
        }
        return falg;
    }
}

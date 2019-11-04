package com.ike.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ike.mapper.OrderBaseMapper;
import com.ike.mapper.ReturnDetailMapper;
import com.ike.mapper.ReturnPlanDetailMapper;
import com.ike.mapper.ext.OrderBaseExtMapper;
import com.ike.mapper.ext.ReturnDetailExtMapper;
import com.ike.pojo.*;
import com.ike.pojo.vo.ReturnDetailSearchVo;
import com.ike.pojo.vo.ReturnDetailStatisitcVo;
import com.ike.pojo.vo.ReturnDetailVo;
import com.ike.service.ReturnDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class ReturnDetailServiceImpl implements ReturnDetailService {

    @Autowired
    private ReturnDetailMapper returnDetailMapper;

    @Autowired
    private OrderBaseMapper orderBaseMapper;

    @Autowired
    private OrderBaseExtMapper orderBaseExtMapper;

    @Autowired
    private ReturnPlanDetailMapper returnPlanDetailMapper;

    @Autowired
    private ReturnDetailExtMapper returnDetailExtMapper;

    @Override
    public List<ReturnDetail> selectAll() {
        return returnDetailMapper.selectByExample(null);
    }

    @Override
    public int insert(ReturnDetail returnDetail) {
        return returnDetailMapper.insertSelective(returnDetail);
    }

    @Override
    public int update(ReturnDetail returnDetail) {
        return returnDetailMapper.updateByPrimaryKeySelective(returnDetail);
    }

    @Override
    public int deleteById(Long id) {
        //查询出改回款的信息
        ReturnDetail returnDetail = returnDetailMapper.selectByPrimaryKey(id);
        //查询出订单的信息
        OrderBase orderBase = orderBaseMapper.selectByPrimaryKey(returnDetail.getOrderBaseId());
        //修改订单的信息：回款总额改为原总额减回款金额 状态为2(付款中)
        OrderBaseExample example = new OrderBaseExample();
        OrderBaseExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(returnDetail.getOrderBaseId());
        OrderBase orderBases = new OrderBase();
        orderBases.setOrderState(Byte.parseByte("2"));
        orderBases.setOrderGetSum(orderBase.getOrderGetSum() - returnDetail.getAmount());
        orderBaseMapper.updateByExampleSelective(orderBases, example);
        return returnDetailMapper.deleteByPrimaryKey(id);
    }

    @Override
    public ReturnDetail selectById(Long id) {
        return returnDetailMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<ReturnDetailVo> selectByOrderBaseId(Long orderBaseId) {
        return returnDetailExtMapper.selectByOrderBaseId(orderBaseId);
    }

    @Override
    public List<ReturnDetailVo> selectByRelationId(Long relationId) {
        return returnDetailExtMapper.selectByRelationId(relationId);
    }

    @Override
    public int countId(Long orderBaseId) {
        QueryWrapper<ReturnDetail> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("order_base_id", orderBaseId);
        return returnDetailMapper.selectCount(queryWrapper);
    }

    @Override
    public int updateAllByEmployeeId(Long oldEmployeeId, Long newEmployeeId) {
        int i = 0;
        ReturnDetailExample example = new ReturnDetailExample();
        example.createCriteria().andEmployeeIdEqualTo(oldEmployeeId);
        List<ReturnDetail> returnDetails = returnDetailMapper.selectByExample(example);
        for (ReturnDetail r : returnDetails) {
            r.setEmployeeId(newEmployeeId);
            r.setModifyTime(LocalDateTime.now());
            i += returnDetailMapper.updateByPrimaryKey(r);
        }
        return i;
    }

    @Override
    public double selectProgress(Long id) {
        //根据订单ID查询出所有的回款记录数
        QueryWrapper<ReturnDetail> queryWrapperById = new QueryWrapper<>();
        queryWrapperById.eq("order_base_id", id);
        int progressCount = returnDetailMapper.selectCount(queryWrapperById);
        //根据订单ID查询出回款计划ID
        QueryWrapper<OrderBase> queryWrapperToId = new QueryWrapper<>();
        queryWrapperToId.select("return_plan_id").eq("id", id);
        Long return_plan_id = orderBaseMapper.selectOne(queryWrapperToId).getReturnPlanId();
        //根据回款计划ID查询出回款计划明细个数
        QueryWrapper<ReturnPlanDetail> queryWrapperToCount = new QueryWrapper<>();
        queryWrapperToCount.eq("return_plan_id", return_plan_id);
        int allCount = returnPlanDetailMapper.selectCount(queryWrapperToCount);
        double progress = progressCount / allCount;
        return progress;
    }

    @Override
    public List<ReturnDetailVo> listReturnDetailSearch(ReturnDetailSearchVo returnDetailSearchVo) {
        return returnDetailExtMapper.listReturnDetailSearch(returnDetailSearchVo);
    }

    @Override
    public int insertReturnDetail(ReturnDetail returnDetail) {
        ReturnDetailExample example = new ReturnDetailExample();
        ReturnDetailExample.Criteria criteria = example.createCriteria();
        criteria.andOrderBaseIdEqualTo(returnDetail.getOrderBaseId());
        List<ReturnDetail> list = returnDetailMapper.selectByExample(example);
        //查看他所有回款记录的总金额
        Double money = returnDetail.getAmount();
        for (ReturnDetail detail : list) {
            money += detail.getAmount();
        }
        //查询订单的回款总金额
        QueryWrapper<OrderBase> WrapperToId = new QueryWrapper<>();
        WrapperToId.select("order_actual_total").eq("id", returnDetail.getOrderBaseId());
        Double allAmount = orderBaseMapper.selectOne(WrapperToId).getOrderActualTotal();
        //如果回款记录的总金额小于订单的回款总金额
        if (money > allAmount) {
            //返回0 代表失败
            return 0;
        } else if (money == allAmount) {
            //如果回款记录的总金额大于等于订单的回款总金额 修改订单状态 1，添加回款金额 添加 返回添加结果
            //查询客户的回款金额
            QueryWrapper<OrderBase> WrapperToIdIt = new QueryWrapper<>();
            WrapperToIdIt.select("order_get_sum").eq("id", returnDetail.getOrderBaseId());
            Double userAllAmount = orderBaseMapper.selectOne(WrapperToIdIt).getOrderGetSum() + returnDetail.getAmount();
            //修改订单状态 1，添加回款金额
            OrderBaseExample pexample = new OrderBaseExample();
            OrderBaseExample.Criteria criterias = pexample.createCriteria();
            criterias.andIdEqualTo(returnDetail.getOrderBaseId());
            OrderBase orderBase = new OrderBase();
            orderBase.setOrderState(Byte.parseByte("1"));
            orderBase.setOrderGetSum(userAllAmount);
            orderBaseMapper.updateByExampleSelective(orderBase, pexample);
            return returnDetailMapper.insertSelective(returnDetail);
        } else {
            QueryWrapper<OrderBase> WrapperToIdIt = new QueryWrapper<>();
            WrapperToIdIt.select("order_get_sum").eq("id", returnDetail.getOrderBaseId());
            OrderBaseExample pexample = new OrderBaseExample();
            OrderBaseExample.Criteria criterias = pexample.createCriteria();
            Double userAllAmount = orderBaseMapper.selectOne(WrapperToIdIt).getOrderGetSum() + returnDetail.getAmount();
            criterias.andIdEqualTo(returnDetail.getOrderBaseId());
            OrderBase orderBase = new OrderBase();
            orderBase.setOrderGetSum(userAllAmount);
            orderBaseMapper.updateByExampleSelective(orderBase, pexample);
            return returnDetailMapper.insertSelective(returnDetail);
        }
    }

    @Override
    public List<ReturnDetailVo> searchReturnDetailByTime(ReturnDetailStatisitcVo returnDetailStatisitcVo) {
        return returnDetailExtMapper.searchReturnDetailByTime(returnDetailStatisitcVo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertList(List<ReturnDetail> returnDetailList) {
        return returnDetailExtMapper.insertList(returnDetailList);
    }

    @Override
    public List<ReturnDetailVo> selectAllList() {
        return returnDetailExtMapper.selectAllList();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int importExcel(Map<String, Double> getSumMap, Map<String, Byte> stateMap, List<ReturnDetail> returnDetail) {
        int i = 0;

        if (!stateMap.isEmpty()){
            if (orderBaseExtMapper.updateOrderStateList(stateMap) > 0){
                i++;
            }
        }

        if (orderBaseExtMapper.updateOrderGetSumList(getSumMap) > 0){
            i++;
        }

        if (returnDetailExtMapper.insertList(returnDetail) > 0){
            i++;
        }

        return  i;
    }

    @Override
    public int deleteBatch(Long[] returnDetailId) {
        for (int i = 0; i < returnDetailId.length; i++) {
            //查询出改回款的信息
            ReturnDetail returnDetail = returnDetailMapper.selectByPrimaryKey(returnDetailId[i]);
            //查询出订单的信息
            OrderBase orderBase = orderBaseMapper.selectByPrimaryKey(returnDetail.getOrderBaseId());
            //修改订单的信息：回款总额改为原总额减回款金额 状态为2(付款中)
            OrderBaseExample example = new OrderBaseExample();
            OrderBaseExample.Criteria criteria = example.createCriteria();
            criteria.andIdEqualTo(returnDetail.getOrderBaseId());
            OrderBase orderBases = new OrderBase();
            orderBases.setOrderState(Byte.parseByte("2"));
            orderBases.setOrderGetSum(orderBase.getOrderGetSum() - returnDetail.getAmount());
            orderBaseMapper.updateByExampleSelective(orderBases, example);
            returnDetailMapper.deleteByPrimaryKey(returnDetailId[i]);
        }
        return 0;
    }
}

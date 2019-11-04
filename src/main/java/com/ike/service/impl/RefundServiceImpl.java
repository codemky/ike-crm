package com.ike.service.impl;

import com.ike.mapper.OrderBaseMapper;
import com.ike.mapper.RefundMapper;
import com.ike.mapper.ext.OrderBaseExtMapper;
import com.ike.mapper.ext.RefundExtMapper;
import com.ike.pojo.OrderBase;
import com.ike.pojo.OrderBaseExample;
import com.ike.pojo.Refund;
import com.ike.pojo.RefundExample;
import com.ike.pojo.vo.RefundSearchVo;
import com.ike.pojo.vo.RefundStatisitcVo;
import com.ike.pojo.vo.RefundVo;
import com.ike.service.RefundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class RefundServiceImpl implements RefundService {

    @Autowired
    private RefundMapper refundMapper;

    @Autowired
    private RefundExtMapper refundExtMapper;

    @Autowired
    private OrderBaseExtMapper orderBaseExtMapper;

    @Autowired
    private OrderBaseMapper orderBaseMapper;

    @Override
    public List<Refund> selectAll() {
        return refundMapper.selectByExample(null);
    }

    @Override
    public List<RefundVo> selectByUserid(Long id) {
        return refundExtMapper.selectByUserid(id);
    }

    @Override
    public int insert(Refund refund) {
        return refundMapper.insertSelective(refund);
    }

    @Override
    public int update(Refund refund) {
        return refundMapper.updateByPrimaryKeySelective(refund);
    }

    @Override
    public int deleteById(Long id) {
        OrderBaseExample example = new OrderBaseExample();
        OrderBaseExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(refundMapper.selectByPrimaryKey(id).getOrderBaseId());
        OrderBase orderBase = new OrderBase();
        orderBase.setOrderState(Byte.parseByte("0"));
        orderBaseMapper.updateByExampleSelective(orderBase, example);
        return refundMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Refund selectById(Long id) {
        return refundMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateAllByEmployeeId(Long old_id, Long new_id) {
        RefundExample example = new RefundExample();
        RefundExample.Criteria criteria = example.createCriteria();
        criteria.andEmployeeIdEqualTo(old_id);
        Refund refund = new Refund();
        refund.setEmployeeId(new_id);
        return refundMapper.updateByExampleSelective(refund,example);
    }

    @Override
    public List<RefundVo> searchRefund(RefundSearchVo searchVo) {
        List<RefundVo> refundVos = refundExtMapper.searchRefund(searchVo);
        return refundVos;
    }

    @Override
    public List<RefundVo> searchRefundByCondition(RefundStatisitcVo refundStatisitcVo) {
        return refundExtMapper.searchRefundByCondition(refundStatisitcVo);
    }

    @Override
    public List<RefundVo> selectAllList() {
        return refundExtMapper.selectAllList();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int importExcel(List<Refund> refundList, Map<String, Byte> stateMap) {
        int i = 0;
        if (refundExtMapper.insertList(refundList) > 0){
            i++;
        }

        if (!stateMap.isEmpty()){
            if (orderBaseExtMapper.updateOrderStateList(stateMap) > 0){
                i++;
            }
        }

        return i;
    }

    @Override
    public int deleteBatch(Long[] refundId) {
        OrderBase orderBase = new OrderBase();
        orderBase.setOrderState(Byte.parseByte("0"));
        for (int i = 0; i < refundId.length; i++) {
            OrderBaseExample example = new OrderBaseExample();
            OrderBaseExample.Criteria criteria = example.createCriteria();
            criteria.andIdEqualTo(refundMapper.selectByPrimaryKey(refundId[i]).getOrderBaseId());
            orderBaseMapper.updateByExampleSelective(orderBase, example);
            refundMapper.deleteByPrimaryKey(refundId[i]);
        }
        return 0;
    }

}

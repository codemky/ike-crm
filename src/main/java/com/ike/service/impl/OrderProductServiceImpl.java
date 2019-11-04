package com.ike.service.impl;

import com.ike.mapper.OrderProductMapper;
import com.ike.mapper.ext.OrderProductExtMapper;
import com.ike.pojo.OrderProduct;
import com.ike.pojo.OrderProductExample;
import com.ike.pojo.vo.OrderProductVo;
import com.ike.service.OrderProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Service
public class OrderProductServiceImpl implements OrderProductService {
    @Autowired
    private OrderProductMapper orderProductMapper;

    @Autowired
    private OrderProductExtMapper orderProductExtMapper;

    @Override
    public List<OrderProduct> selectAll() {
        return orderProductMapper.selectByExample(null);
    }

    @Override
    public int insert(OrderProduct orderProduct) {
        orderProduct.setCreateTime(LocalDateTime.now());
        return orderProductMapper.insertSelective(orderProduct);
    }

    @Override
    public int update(OrderProduct orderProduct) {
        return orderProductMapper.updateByPrimaryKeySelective(orderProduct);
    }

    @Override
    public int deleteById(Long id) {
        return orderProductMapper.deleteByPrimaryKey(id);
    }

    @Override
    public OrderProduct selectById(Long id) {
        return orderProductMapper.selectByPrimaryKey(id);
    }

    // @Override
    // public List<OrderProduct> selectAllByOrderBaseId(Long orderBaseId) {
    //     OrderProductExample example = new OrderProductExample();
    //     example.createCriteria().andOrderBaseIdEqualTo(orderBaseId);
    //     List<OrderProduct> orderProducts = orderProductMapper.selectByExample(example);
    //     return orderProducts;
    // }

    @Override
    public Double selectMoney(Long orderBaseId) {
        OrderProductExample example = new OrderProductExample();
        example.createCriteria().andOrderBaseIdEqualTo(orderBaseId);
        List<OrderProduct> orderProducts = orderProductMapper.selectByExample(example);
        double money = 0;
        for (OrderProduct o:orderProducts) {
            money+=o.getActualPrice();
        }
        return money;
    }

    @Override
    public List<OrderProductVo> selectAllByOrderBaseId(Long orderBaseId) {
        List<OrderProductVo> orderProductVos = orderProductExtMapper.selectAllByOrderBaseId(orderBaseId);
        return orderProductVos;
    }

    @Override
    public List<OrderProductVo> showOrderProductList(Long id) {
        return orderProductExtMapper.selectOrderProductList(id);
    }

    @Override
    public int deleteListByOrderBaseId(Long orderBaseId) {
        return orderProductExtMapper.deleteListByOrderBaseId(orderBaseId);
    }

    @Override
    public int insertList(List<OrderProductVo> orderProductVoList) {
        return orderProductExtMapper.insertList(orderProductVoList);
    }
}

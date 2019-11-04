package com.ike.service.impl;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.ike.common.constans.CodeMsg;
import com.ike.common.result.Result;
import com.ike.mapper.*;
import com.ike.mapper.ext.*;
import com.ike.pojo.*;
import com.ike.pojo.vo.*;
import com.ike.service.OrderBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class OrderBaseServiceImpl implements OrderBaseService {

    @Autowired
    private OrderBaseMapper orderBaseMapper;
    @Autowired
    private OrderProductMapper orderProductMapper;
    @Autowired
    private OrderProductExtMapper orderProductExtMapper;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private ReturnDetailMapper returnDetailMapper;
    @Autowired
    private ReturnDetailExtMapper returnDetailExtMapper;
    @Autowired
    private RefundMapper refundMapper;
    @Autowired
    private RefundExtMapper refundExtMapper;
    @Autowired
    private OrderBaseExtMapper orderBaseExtMapper;

    @Override
    public List<OrderBase> selectAll() {
        return orderBaseMapper.selectByExample(null);
    }

    @Override
    public int insert(OrderBase orderBase) {

        LocalDateTime time = LocalDateTime.now();
        orderBase.setModifyTime(time);
//        orderBase.setOrderTime(time);
        orderBase.setOrderState(new Byte("0"));

        return orderBaseMapper.insertSelective(orderBase);
    }

    @Override
    public int update(OrderBase orderBase) {
        orderBase.setModifyTime(LocalDateTime.now());
        return orderBaseMapper.updateByPrimaryKeySelective(orderBase);
    }

    @Override
    public int deleteById(Long id) {
        OrderBase orderBase = orderBaseMapper.selectByPrimaryKey(id);
        orderBase.setOrderState(new Byte("5"));
        orderBase.setModifyTime(LocalDateTime.now());
        return orderBaseMapper.updateByPrimaryKey(orderBase);
    }

    @Override
    public OrderBase selectById(Long id) {
        return orderBaseMapper.selectByPrimaryKey(id);
    }


    @Override
    public List<OrderBaseVo> selectOrderBaseAll() {
        return orderBaseExtMapper.selectOrderBaseAll();
    }

    @Override
    public List<OrderBaseVo> selectOrderBaseByStatus(Byte order_state) {
        return orderBaseExtMapper.selectOrderBaseByEemployeeIdAndStatus(null, order_state);
    }

    @Override
    public List<OrderBaseVo> selectOrderBaseByEemployeeIdAndStatus(Long employee_id, Byte order_state) {
        return orderBaseExtMapper.selectOrderBaseByEemployeeIdAndStatus(employee_id, order_state);
    }

    @Override
    public List<OrderBaseVo> selectOrderBaseByEemployeeId(Long employee_id) {
        return orderBaseExtMapper.selectOrderBaseByEemployeeId(employee_id);
    }

    @Override
    public OrderDetailVo selectOrderBaseByOrderId(Long id) {
        return orderBaseExtMapper.selectOrderBaseByOrderId(id);
    }

    @Override
    public List<OrderBaseVo> searchOrderBase(OrderBaseSearchVo searchVO) {
        return orderBaseExtMapper.searchOrderBase(searchVO);
    }

    @Override
    public int deleteAllByEmployeeId(Long EmployeeId) {
        OrderBaseExample example = new OrderBaseExample();
        example.createCriteria().andEmployeeIdEqualTo(EmployeeId);
        List<OrderBase> list = orderBaseMapper.selectByExample(example);
        int i = 0;
        //删除订单产品以及订单基本信息
        OrderProductExample orderProductExample = new OrderProductExample();
        for (OrderBase orderBase : list) {
            //删除退款信息
            RefundExample refundExample = new RefundExample();
            refundExample.createCriteria().andOrderBaseIdEqualTo(orderBase.getId());
            i += refundMapper.deleteByExample(refundExample);
            //删除回款记录
            ReturnDetailExample returnDetailExample = new ReturnDetailExample();
            returnDetailExample.createCriteria().andOrderBaseIdEqualTo(orderBase.getId());
            i += returnDetailMapper.deleteByExample(returnDetailExample);
            //删除订单产品表
            orderProductExample.createCriteria().andOrderBaseIdEqualTo(orderBase.getId());
            orderProductMapper.deleteByExample(orderProductExample);
            //删除订单
            orderBaseMapper.deleteByPrimaryKey(orderBase.getId());
            i += 1;
        }

        return i;
    }

    @Override
    public int updateAllByEmployeeId(Long old_id, Long new_id, User user) {
        int i = 0;
        OrderBaseExample example = new OrderBaseExample();
        example.createCriteria().andEmployeeIdEqualTo(old_id);
        List<OrderBase> orderBases = orderBaseMapper.selectByExample(example);
        for (OrderBase o : orderBases) {
            o.setEmployeeId(new_id);
            o.setModifyTime(LocalDateTime.now());
            o.setModifyUserId(user.getId());
            i += orderBaseMapper.updateByPrimaryKey(o);
        }
        RefundExample rexample = new RefundExample();
        RefundExample.Criteria criteria = rexample.createCriteria();
        criteria.andEmployeeIdEqualTo(old_id);
        Refund refund = new Refund();
        refund.setEmployeeId(new_id);
        refund.setModifyTime(LocalDateTime.now());
        refund.setModifyUserId(user.getId());
        refundMapper.updateByExampleSelective(refund, rexample);
        ReturnDetailExample rdexample = new ReturnDetailExample();
        rdexample.createCriteria().andEmployeeIdEqualTo(old_id);
        List<ReturnDetail> returnDetails = returnDetailMapper.selectByExample(rdexample);
        for (ReturnDetail r : returnDetails) {
            r.setEmployeeId(new_id);
            r.setModifyTime(LocalDateTime.now());
            r.setModifyUserId(user.getId());
            i += returnDetailMapper.updateByPrimaryKey(r);
        }
        return i;
    }

    @Override
    public Map<String, Integer> selectCountAllByRelationId(Long relationId) {
        Map<String, Integer> map = new HashMap<>();
        OrderBaseExample example = new OrderBaseExample();
        example.createCriteria().andRelationIdEqualTo(relationId);

        //获取订单表条数
        List<OrderBase> list = orderBaseMapper.selectByExample(example);
        map.put("订单基本信息", list.size());

        //获取产品订单条数
        int productCount = 0;
        OrderProductExample orderProductExample = new OrderProductExample();
        for (OrderBase orderBase : list) {
            //删除订单产品表
            orderProductExample.createCriteria().andOrderBaseIdEqualTo(orderBase.getId());
            int count = orderProductMapper.countByExample(orderProductExample);
            productCount += count;
        }
        map.put("产品订单", productCount);

        //获取退款信息条数
        RefundExample refundExample = new RefundExample();

        refundExample.createCriteria().andRelationIdEqualTo(relationId);

        // refundExample.createCriteria().andEmployeeIdEqualTo(employeeid);
        map.put("退款信息", refundMapper.countByExample(refundExample));

        //获取回款记录条数
        ReturnDetailExample returnDetailExample = new ReturnDetailExample();
        returnDetailExample.createCriteria().andRelationIdEqualTo(relationId);
        // returnDetailExample.createCriteria().andEmployeeIdEqualTo(employeeid);
        map.put("回款记录", returnDetailMapper.countByExample(returnDetailExample));

        return map;
    }

    @Override
    public Map<String, Integer> selectCountAllByEmployeeId(Long employeeid) {
        Map<String, Integer> map = new HashMap<>();
        OrderBaseExample example = new OrderBaseExample();
            example.createCriteria().andEmployeeIdEqualTo(employeeid);

        //获取订单表条数
        List<OrderBase> list = orderBaseMapper.selectByExample(example);
        map.put("订单基本信息", list.size());

        //获取产品订单条数
        int productCount = 0;
        OrderProductExample orderProductExample = new OrderProductExample();
        for (OrderBase orderBase : list) {
            //删除订单产品表
            orderProductExample.createCriteria().andOrderBaseIdEqualTo(orderBase.getId());
            int count = orderProductMapper.countByExample(orderProductExample);
            productCount += count;
        }
        map.put("产品订单", productCount);

        //获取退款信息条数
        RefundExample refundExample = new RefundExample();

            refundExample.createCriteria().andEmployeeIdEqualTo(employeeid);

        // refundExample.createCriteria().andEmployeeIdEqualTo(employeeid);
        map.put("退款信息", refundMapper.countByExample(refundExample));

        //获取回款记录条数
        ReturnDetailExample returnDetailExample = new ReturnDetailExample();
            returnDetailExample.createCriteria().andEmployeeIdEqualTo(employeeid);
        // returnDetailExample.createCriteria().andEmployeeIdEqualTo(employeeid);
        map.put("回款记录", returnDetailMapper.countByExample(returnDetailExample));

        return map;
    }

    @Override
    public int deleteBatch(Long[] order_base_id) {
//        List<Long> list = Arrays.asList(order_base_id);
        int i = 0;
        for (int j = 0; j < order_base_id.length; j++) {
            System.out.println(order_base_id[j]);
            OrderBase orderBase = orderBaseMapper.selectByPrimaryKey(order_base_id[j]);
            orderBase.setOrderState(new Byte("5"));
            orderBase.setModifyTime(LocalDateTime.now());
            orderBaseMapper.updateByPrimaryKey(orderBase);
            i++;
        }
        return i++;
    }

    @Override
    public int deleteAllByOrderBaseId(Long order_base_id) {
        int deleteCount = 0;
        deleteCount += orderBaseMapper.deleteByPrimaryKey(order_base_id);
        deleteCount += orderProductExtMapper.deleteListByOrderBaseId(order_base_id);
        deleteCount += returnDetailExtMapper.deleteListByOrderBaseId(order_base_id);
        deleteCount += refundExtMapper.deleteListByOrderBaseId(order_base_id);
        return deleteCount;
    }

    @Override
    public List<OrderBaseVo> searchOrderBaseByTime(OrderBaseStatisitcVO orderBaseStatisitcVO) {
        System.out.println(orderBaseStatisitcVO.getEmployeeId());
        List<OrderBaseVo> orderBaseVos = orderBaseExtMapper.searchOrderBaseByTime(orderBaseStatisitcVO);
        return orderBaseVos;
    }

    @Override
    public int updateStateById(Long order_base_id) {
        OrderBaseExample example = new OrderBaseExample();
        OrderBaseExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(order_base_id);
        OrderBase orderBase = new OrderBase();
        orderBase.setOrderState(Byte.parseByte("4"));
        orderBaseMapper.updateByExampleSelective(orderBase, example);
        return 0;
    }

    @Override
    public int deleteAllByRelationId(Long relation_id) {
        OrderBaseExample example = new OrderBaseExample();
        example.createCriteria().andRelationIdEqualTo(relation_id);
        List<OrderBase> list = orderBaseMapper.selectByExample(example);
        int i = 0;
        //删除订单产品以及订单基本信息
        OrderProductExample orderProductExample = new OrderProductExample();
        for (OrderBase orderBase : list) {
            //删除退款信息
            RefundExample refundExample = new RefundExample();
            refundExample.createCriteria().andOrderBaseIdEqualTo(orderBase.getId());
            i += refundMapper.deleteByExample(refundExample);
            //删除回款记录
            ReturnDetailExample returnDetailExample = new ReturnDetailExample();
            returnDetailExample.createCriteria().andOrderBaseIdEqualTo(orderBase.getId());
            i += returnDetailMapper.deleteByExample(returnDetailExample);
            //删除订单产品表
            orderProductExample.createCriteria().andOrderBaseIdEqualTo(orderBase.getId());
            orderProductMapper.deleteByExample(orderProductExample);
            //删除订单
            orderBaseMapper.deleteByPrimaryKey(orderBase.getId());
            i += 1;
        }
        return i;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertList(List<OrderBase> orderBaseList) {
        return orderBaseExtMapper.insertList(orderBaseList);
    }

    @Override
    public List<OrderBaseVo> selectOrderBaseInfo() {
        return orderBaseExtMapper.selectOrderBaseInfo();
    }

    @Override
    public void orderBaseExport() {
        try {
            OutputStream out = new FileOutputStream("d:/test.xlsx");
            ExcelWriter writer = EasyExcelFactory.getWriter(out);
            // List<RefundExportVo> refundExportVos = refundExportExtMapper.selectAllToExport();
            List<OrderExportVo> orderExportVos = orderBaseExtMapper.orderBaseExport();
            for (int i = 0; i < orderExportVos.size(); i++) {
                System.out.println(orderExportVos.get(i).getOrderBaseId());
            }

            Sheet sheet = new Sheet(1,0,OrderExportVo.class);
            sheet.setSheetName("测试");
            writer.write(orderExportVos,sheet);
            writer.finish();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getOrderDetailVo(OrderDetailVo orderDetailVo) {
        //获取订单产品集合
        List<OrderProductVo> list = orderDetailVo.getOrderProductVoList();
        //遍历获取订单各项总额及订单产品集合
        Double orderTotal = 0.0;
        // 订单总成本
        Double orderCost = 0.0;
        List<OrderProductVo> orderProductList = new ArrayList<>();
        for (OrderProductVo p : list) {
            //创建新订单产品对象
            OrderProductVo orderProductVo= new OrderProductVo();
            orderProductVo.setId(p.getId());
            orderProductVo.setOrderCount(p.getOrderCount());
            orderProductVo.setProductId(p.getProductId());
            //获取产品详情
            Product product = productMapper.selectByPrimaryKey(p.getProductId());
            if (product == null){
                return "产品编号为"+p.getProductId()+"的产品不存在！";
            }
            //获取订单总额
            orderTotal += product.getSalePrice() * p.getOrderCount();
            //获取订单总成本
            orderCost += product.getCost() * p.getOrderCount();

            //获取订单产品成交总价
            orderProductVo.setTotalPrice(orderTotal);
            //装入集合
            orderProductList.add(orderProductVo);
        }
        //重新设置订单价格
        orderDetailVo.setOrderCost(orderCost);
        orderDetailVo.setOrderTotal(orderTotal);
        //重新放入订单产品列表
        orderDetailVo.setOrderProductVoList(orderProductList);

        return null;
    }

}

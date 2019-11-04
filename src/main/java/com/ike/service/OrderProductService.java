package com.ike.service;

import com.ike.pojo.OrderProduct;
import com.ike.pojo.vo.OrderProductVo;

import java.util.List;

public interface OrderProductService {
    /**
     * 显示所有订单产品
     * @return
     */
    List<OrderProduct> selectAll();

    /**
     * 新增订单产品信息
     * @param orderProduct
     * @return
     */
    int insert(OrderProduct orderProduct);

    /**
     * 更新订单产品信息
     * @param orderProduct
     * @return
     */
    int update(OrderProduct orderProduct);

    /**
     * 根据主键id删除订单内产品
     * @param id
     * @return
     */
    int deleteById(Long id);

    /**
     * 根据id选择获取订单产品信息
     * @param id
     * @return Orderproduct
     */
    OrderProduct selectById(Long id);

    /**
     * 根据订单id查询订单产品信息
     * @param orderBaseId
     * @return
     */
//     List<OrderProduct> selectListByOrderBaseId(Long orderBaseId);

    /**
     * 获取当前订单的金额
     * @param orderBaseId
     * @return
     */
    Double selectMoney(Long orderBaseId);

    /**
     * 根据订单的id进行订单产品信息的查询
     * @param orderBaseId
     * @return
     */
    List<OrderProductVo> selectAllByOrderBaseId(Long orderBaseId);

    /**
     * 根据订单的id来查询他的订单产品信息
     *
     * @params [id]
     * @Return java.util.List<com.ike.pojo.vo.OrderProductVo>
     * @Date 2019/10/18 21:55
     **/
    List<OrderProductVo> showOrderProductList(Long id);

    /**
     * 根据订单id批量删除订单产品
     * @param orderBaseId
     * @return
     */
    int deleteListByOrderBaseId(Long orderBaseId);

    /**
     * 批量插入订单产品
     * @param orderProductVoList
     * @return
     */
    int insertList(List<OrderProductVo> orderProductVoList);
}

package com.ike.mapper.ext;

import com.ike.pojo.vo.OrderProductVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description TODO
 * @Date 2019/10/18 21:56
 */
@Repository
public interface OrderProductExtMapper {
    List<OrderProductVo> selectOrderProductList(@Param("id") Long id);

    /**
     * 根据订单id来找订单产品信息
     * @param orderBaseId
     * @return
     */
    List<OrderProductVo> selectAllByOrderBaseId(@Param("order_base_id")Long orderBaseId);

    /**
     * 批量更新产品订单
     *
     * @param orderProductVoList
     * @return
     */
    List<OrderProductVo> updateList(@Param("PrderProductVo") List<OrderProductVo> orderProductVoList);

    /**
     * 根据订单id批量删除订单产品
     * @param orderBaseId
     * @return
     */
    int deleteListByOrderBaseId(@Param("order_base_id")Long orderBaseId);

    /**
     * 批量插入订单产品
     * @param orderProductVoList
     * @return
     */
    int insertList(@Param("PrderProductVo") List<OrderProductVo> orderProductVoList);
}

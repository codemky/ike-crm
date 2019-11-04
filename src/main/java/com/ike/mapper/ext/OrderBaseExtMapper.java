package com.ike.mapper.ext;

import com.ike.pojo.OrderBase;
import com.ike.pojo.vo.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Description TODO
 * @Date 2019/10/18 11:20
 */
@Repository
public interface OrderBaseExtMapper {

    /**
     * 查询所有订单
     * @return
     */
    List<OrderBaseVo> selectOrderBaseAll();

    /**
     * 根据负员工ID和订单状态查询订单
     * @param employee_id (可选，为null时则搜索全部订单)
     * @param order_state（必须要有状态）
     * @return
     */
    List<OrderBaseVo> selectOrderBaseByEemployeeIdAndStatus(@Param("employee_id") Long employee_id, @Param("order_state") Byte order_state);

    /**
     * 根据员工id查找订单
     * @param employee_id
     * @return
     */
    List<OrderBaseVo> selectOrderBaseByEemployeeId(@Param("employee_id") Long employee_id);

    /**
     * 根据订单ID查询订单
     * @param id
     * @return
     */
    OrderDetailVo selectOrderBaseByOrderId(@Param("id") Long id);

    /**
     * 根据多条件搜索订单列表
     * @param searchVO
     * @return
     */
    List<OrderBaseVo> searchOrderBase(@Param("searchVO") OrderBaseSearchVo searchVO);

    /**
     * 根据时间来查询订单
     * @param orderBaseStatisitcVO
     * @return
     */
    List<OrderBaseVo> searchOrderBaseByTime(@Param("OrderBaseStatisitcVO") OrderBaseStatisitcVO orderBaseStatisitcVO);


    /**
     * 批量插入
     * @param orderBase
     * @return
     */
    int insertList(@Param("orderBaseList")List<OrderBase> orderBase);

    /**
     * 找到所有的导出对象
     * @return
     */
    List<OrderExportVo> orderBaseExport();

    /**
     * 获取订单id，金额，已回款金额，分期数，已回款次数
     * @return
     */
    List<OrderBaseVo> selectOrderBaseInfo();

    /**
     * 批量更新订单状态
     * @param stateMap 以订单为key，状态为value的map集合
     * @return
     */
    int updateOrderStateList(@Param("stateMap") Map<String, Byte> stateMap);

    /**
     * 批量更新订单总回款金额
     * @param getSumMap 以订单为key，订单总回款金额为value的map集合
     * @return
     */
    int updateOrderGetSumList(@Param("getSumMap") Map<String, Double> getSumMap);

}

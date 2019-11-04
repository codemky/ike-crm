package com.ike.service;

import com.ike.pojo.OrderBase;
import com.ike.pojo.User;
import com.ike.pojo.vo.OrderBaseSearchVo;
import com.ike.pojo.vo.OrderBaseStatisitcVO;
import com.ike.pojo.vo.OrderBaseVo;
import com.ike.pojo.vo.OrderDetailVo;

import java.util.List;
import java.util.Map;


public interface OrderBaseService {
    /**
     * 显示所有订单信息
     * @return
     */
    List<OrderBase> selectAll();

    /**
     * 新增订单基本信息
     * @param orderBase
     * @return
     */
    int insert(OrderBase orderBase);

    /**
     * 更新订单信息
     * @param orderBase
     * @return
     */
    int update(OrderBase orderBase);

    /**
     * 根据主键id删除
     * @param id
     * @return
     */
    int deleteById(Long id);

    /**
     * 根据id选择获取订单信息
     * @param id
     * @return
     */
    OrderBase selectById(Long id);

    /**
     * 查询所有订单
     * @return
     */
    List<OrderBaseVo> selectOrderBaseAll();

    /**
     * 根据订单状态查询所有订单
     * @param order_state
     * @return
     */
    List<OrderBaseVo> selectOrderBaseByStatus(Byte order_state);

    /**
     * 根据负责员工ID和订单状态查询订单
     * @param employee_id (可选，为null时则搜索全部订单)
     * @param order_state（必须要有状态）
     * @return
     */
    List<OrderBaseVo> selectOrderBaseByEemployeeIdAndStatus(Long employee_id, Byte order_state);

    /**
     * 根据负责员工ID查询所有订单
     * @param employee_id (可选，为null时则搜索全部订单)
     * @return
     */
    List<OrderBaseVo> selectOrderBaseByEemployeeId(Long employee_id);

    /**
     * 根据订单ID查询订单
     * @param id
     * @return
     */
    OrderDetailVo selectOrderBaseByOrderId(Long id);

    /**
     * 根据条件搜索订单
     * @param searchVO
     * @return
     */
    List<OrderBaseVo> searchOrderBase(OrderBaseSearchVo searchVO);

    /**
     * 根据负责人id删除订单模块所有关联记录(包括：订单基本信息，退款记录，回款记录和产品订单表)
     * @param employee_id
     * @return
     */
    int deleteAllByEmployeeId(Long employee_id);

    /**
     * 根据负责人id更新旧负责人所有关联记录为新负责人的id(包括：订单基本信息，退款记录，回款记录和产品订单表)
     * @param old_employee_id 旧负责人id
     * @param new_employee_id 新负责人id
     * @return
     */
    int updateAllByEmployeeId(Long old_employee_id, Long new_employee_id, User user);

    /**
     * 根据负责人id查询相关记录的数量(包括：订单基本信息，退款记录，回款记录和产品订单表)
     * @param employee_id
     * @return StatisticsVo集合，对象的dataName对应表名，dataNum对应数据条数
     */
    Map<String,Integer> selectCountAllByEmployeeId(Long employee_id);

    /**
     * 根据客户联系人id查询相关记录的数量(包括：订单基本信息，退款记录，回款记录和产品订单表)
     * @param relationId
     * @return StatisticsVo集合，对象的dataName对应表名，dataNum对应数据条数
     */
    Map<String,Integer>  selectCountAllByRelationId(Long relationId);
    /**
     * 根据订单ID批量删除订单
     *
     * @params [order_base_id]
     * @Return int
     * @Date 2019/10/19 0:48
     **/
    int deleteBatch(Long[] order_base_id);

    /**
     * 根据订单id物理删除订单以及其关联的回退款信息
     * @param order_base_id
     * @return
     */
    int deleteAllByOrderBaseId(Long order_base_id);

    /**
     * 根据时间，负责人id ，状态进行多条件查询
     * @param orderBaseStatisitcVO
     * @return
     */
    List<OrderBaseVo> searchOrderBaseByTime(OrderBaseStatisitcVO orderBaseStatisitcVO);

    /**
     * 添加退款信息时，把订单状态改为退款成功
     *
     * @params [order_base_id]
     * @Return int
     * @Date 2019/10/21 22:46
     **/
    int updateStateById(Long order_base_id);

    /**
     * 根据联系人id删除订单模块所有关联记录
     *
     * @params []
     * @Return int
     * @Date 2019/10/22 21:48
     **/
    int deleteAllByRelationId(Long relation_id);

    /**
     * 批量插入
     * @param orderBaseList
     * @return
     */
    int insertList(List<OrderBase> orderBaseList);

    /**
     * 获取订单id，金额，已回款金额，分期数，已回款次数
     * @return
     */
    List<OrderBaseVo> selectOrderBaseInfo();

    /**
     * 订单表导出
     */
    void orderBaseExport();

    /**
     * 根据已有的orderDetailVo对象重新计算金额等数据
     * @param orderDetailVo
     * @return
     */
    String getOrderDetailVo(OrderDetailVo orderDetailVo);
}

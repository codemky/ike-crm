package com.ike.service;

import com.ike.pojo.Refund;
import com.ike.pojo.vo.RefundSearchVo;
import com.ike.pojo.vo.RefundStatisitcVo;
import com.ike.pojo.vo.RefundVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface RefundService {

    /**
     * 显示所有退款记录
     * @return
     */
    List<Refund> selectAll();

    /**
     * 显示用户负责的所有退款记录,并添加对应id的人员名字
     * @param id
     * @return
     */
    List<RefundVo> selectByUserid(Long id);

    /**
     * 新增退款记录
     * @param refund
     * @return
     */
    int insert(Refund refund);

    /**
     * 更新退款记录
     * @param refund
     * @return
     */
    int update(Refund refund);

    /**
     * 根据主键id删除退款记录,并修改订单状态为0(已退款)
     * @param id
     * @return
     */
    int deleteById(Long id);

    /**
     * 根据id选择获取退款记录
     * @param id
     * @return
     */
    Refund selectById(Long id);

    /**
    * 修改负责人为新的负责人
    * @params [new_id, old_id]
    * @Return int
    * @Date 2019/10/14 23:19
    **/
    int updateAllByEmployeeId(Long old_id, Long new_id);

    /**
     * 根据多条件进行退款查询
     * @param searchVo
     * @return
     */
    List<RefundVo> searchRefund(RefundSearchVo searchVo);

    /**
     * 按时间搜索退款信息
     *
     * @params [refundStatisitcVo]
     * @Return java.util.List<com.ike.pojo.vo.RefundVo>
     * @Date 2019/10/22 0:28
     **/
    List<RefundVo> searchRefundByCondition(RefundStatisitcVo refundStatisitcVo);

    /**
     * 查询全部退款,并添加对应id的人员名字
     *
     * @params []
     * @Return java.util.List<com.ike.pojo.vo.RefundVo>
     * @Date 2019/10/24 9:48
     **/
    List<RefundVo> selectAllList();

    /**
     * 导入excel回款信息到数据库表，并更新订单相关数据
     * @param refundList 要插入的退款记录集合
     * @param stateMap stateMap 以订单为key，状态为value的map集合
     * @return
     */
    int importExcel(@Param("refundList")List<Refund> refundList, Map<String, Byte> stateMap);

    /**
     * 根据退款ID的数组进行批量删除
     *
     * @params [refundId]
     * @Return int
     * @Date 2019/10/28 18:20
     **/
    int deleteBatch(Long[] refundId);
}

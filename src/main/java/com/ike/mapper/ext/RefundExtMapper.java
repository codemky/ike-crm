package com.ike.mapper.ext;

import com.ike.pojo.Refund;
import com.ike.pojo.vo.RefundSearchVo;
import com.ike.pojo.vo.RefundStatisitcVo;
import com.ike.pojo.vo.RefundVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Date: 2019-10-20 23:02
 * @Description:(描述)
 */
@Repository
public interface RefundExtMapper {

    /**
     * 根据条件进行查询
     * @param searchVo
     * @return
     */
    List<RefundVo> searchRefund(@Param("searchVO")RefundSearchVo searchVo);

    /**
     * 根据条件进行查询
     *
     * @Description TODO
     * @Return java.util.List<com.ike.pojo.vo.RefundVo>
     * @Date 2019/10/24 9:51
     **/
    List<RefundVo> searchRefundByCondition(@Param("searchVO") RefundStatisitcVo searchVo);

    /**
     * 查询所有的退款记录，并添加对应ID的人员名字
     *
     * @params []
     * @Return java.util.List<com.ike.pojo.vo.RefundVo>
     * @Date 2019/10/24 9:52
     **/
    List<RefundVo> selectAllList();

    /**
     * 显示用户负责的所有退款记录,并添加对应id的人员名字
     *
     * @params [id]
     * @Return java.util.List<com.ike.pojo.vo.RefundVo>
     * @Date 2019/10/24 10:06
     **/
    List<RefundVo> selectByUserid(@Param("id") Long id);

    /**
     * 批量插入
     * @param refundList
     * @return
     */
    int insertList(@Param("refundList")List<Refund> refundList);

    /**
     * 根据订单id批量删除订单产品
     * @param orderBaseId
     * @return
     */
    int deleteListByOrderBaseId(@Param("order_base_id")Long orderBaseId);
}

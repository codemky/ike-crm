package com.ike.mapper.ext;

import com.ike.pojo.ReturnDetail;
import com.ike.pojo.ReturnPlan;
import com.ike.pojo.vo.ReturnDetailExportVo;
import com.ike.pojo.vo.ReturnDetailSearchVo;
import com.ike.pojo.vo.ReturnDetailStatisitcVo;
import com.ike.pojo.vo.ReturnDetailVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description TODO
 * @Date 2019/10/20 22:22
 */
@Repository
public interface ReturnDetailExtMapper {

    /**
     * 多条件查询订单回款记录
     *
     * @params [searchVo]
     * @Return java.util.List<com.ike.pojo.vo.ReturnDetailVo>
     * @Date 2019/10/24 10:29
     **/
    List<ReturnDetailVo> listReturnDetailSearch(@Param("searchVO") ReturnDetailSearchVo searchVo);

    /**
     * 多条件查询订单回款记录,并根据时间的年月日进行查询
     *
     * @params [searchVo]
     * @Return java.util.List<com.ike.pojo.vo.ReturnDetailVo>
     * @Date 2019/10/24 10:31
     **/
    List<ReturnDetailVo> searchReturnDetailByTime(@Param("searchVO") ReturnDetailStatisitcVo searchVo);

    /**
     * @params [returnDetailList]
     * @Return int
     * @Date 2019/10/24 10:35
     **/
    int insertList(@Param("returnDetailList") List<ReturnDetail> returnDetailList);

    /**
     * 查询所有的回款记录，并添加对应ID的人员名字
     *
     * @params []
     * @Return java.util.List<com.ike.pojo.vo.ReturnDetailVo>
     * @Date 2019/10/24 11:17
     **/
    List<ReturnDetailVo> selectAllList();

    /**
     * 根据订单ID查询回款记录，并添加对应ID的人员名字
     *
     * @params []
     * @Return java.util.List<com.ike.pojo.vo.ReturnDetailVo>
     * @Date 2019/10/24 11:51
     **/
    List<ReturnDetailVo> selectByOrderBaseId(Long orderBaseId);

    /**
     * 根据联系人ID查询回款记录，并添加对应ID的人员名字
     *
     * @params [relationId]
     * @Return java.util.List<com.ike.pojo.vo.ReturnDetailVo>
     * @Date 2019/10/24 11:55
     **/
    List<ReturnDetailVo> selectByRelationId(Long relationId);

    /**
     * 导出回款记录表
     * @return
     */
    List<ReturnDetailExportVo> exportReturnDetail();

    /**
     * 根据订单id批量删除订单产品
     * @param orderBaseId
     * @return
     */
    int deleteListByOrderBaseId(@Param("order_base_id")Long orderBaseId);

}

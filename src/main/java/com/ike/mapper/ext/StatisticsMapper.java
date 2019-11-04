package com.ike.mapper.ext;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ike.pojo.vo.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Date：2019-10-14 8:43
 * @Description：<描述>
 */
@Repository
public interface StatisticsMapper {

    /**
     * 根据用户阶段统计客户数量
     * @return
     */
    List<StatisticsVo> selectCustomerCountByStageName();

    /**
     * 根据用户等级统计客户数量
     * @return
     */
    List<StatisticsVo> selectCustomerCountByLevelName();

    /**
     * 根据用户来源统计客户数量
     * @return
     */
    List<StatisticsVo> selectCustomerCountByOrigin();

    /**
     * 根据客户住址获取客户数量
     * @return
     */
    List<StatisticsVo> selectCustomerCountByAddress();

    /**
     * 客户统计-按年月日-客户新增数 / 客户跟进记录 / 跟进客户数
     */

    /**
     * 客户新增数
     */
    List<CustomerStatisticVO> staCustomerAddByYear();

    List<CustomerStatisticVO> staCustomerAddByMonth(@Param("year") String year);

    List<CustomerStatisticVO> staCustomerAddByDay(@Param("yearAndMonth") String yearAndMonth);

    /**
     * 客户跟进记录
     */
    List<CustomerStatisticVO> staFollowAddByYear();

    List<CustomerStatisticVO> staFollowAddByMonth(@Param("year") String year);

    List<CustomerStatisticVO> staFollowAddByDay(@Param("yearAndMonth") String yearAndMonth);

    /**
     * 跟进客户数
     */
    List<CustomerStatisticVO> staCustomerFollowByYear();

    List<CustomerStatisticVO> staCustomerFollowByMonth(@Param("year") String year);

    List<CustomerStatisticVO> staCustomerFollowByDay(@Param("yearAndMonth") String yearAndMonth);


    /**
     * 订单统计 -- 按时间段统计订单某项金额的总和统计
     * @param orderBaseStatisitcVO
     * @return
     */
    List<StatisticsVo> statisticalOrderBaseTotalByDateArea(@Param("OrderBaseStatisitcVO")OrderBaseStatisitcVO orderBaseStatisitcVO);
    /**
     * 订单统计 -- 根据条件，按时间统计订单某项金额的总和统计
     * @param orderBaseStatisitcVO
     * @return
     */
    List<StatisticsVo> statisticalOrderBaseTotal(@Param("OrderBaseStatisitcVO")OrderBaseStatisitcVO orderBaseStatisitcVO);
    /**
     * 产品销量---按产品名称统计
     * 按年月日或指定时间段统计所有产品的成交总额、成交数量、成交客户数
     * @param page
     * @param staProSearchVO
     * @return
     */
    IPage<ProStatisticVO> staProductByName(@Param("page") Page<ProStatisticVO> page, @Param("searchVO")StaProSearchVO staProSearchVO);

    List<StaProductClassVO> staProductByClass(@Param("searchVO") StaProSearchVO staProSearchVO);


}

package com.ike.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ike.pojo.Customer;
import com.ike.pojo.vo.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**

 * @author wgm
 * @version 1.0
 * @date 2019/10/15 10:16
 **/
@Repository
public interface StatisticsService {

    /**
     * 新增客户数
     */
    List<CustomerStatisticVO> staCustomerAdd(String dateType, String date);

    /**
     * 客户跟进记录
     */

    List<CustomerStatisticVO> staFollowAdd(String dateType, String date);

    /**
     * 跟进客户数
     */
    List<CustomerStatisticVO> staCustomerFollow(String dateType, String date);


    /**
     * 产品销量统计
     * @param page
     * @param searchVO
     * @return
     */
    IPage<ProStatisticVO> staProductByName(Page<ProStatisticVO> page, StaProSearchVO searchVO);

    List<StaProductClassVO> staProductByClass(StaProSearchVO searchVO);

    /**
     * 订单统计 -- 按时间段统计订单某项金额的总和统计
     * @param orderBaseStatisitcVO
     * @return
     */
    List<StatisticsVo> staOrderBaseTotalByDateArea(OrderBaseStatisitcVO orderBaseStatisitcVO);
    /**
     * 根据条件，按时间统计订单某项金额的总和统计
     * @param orderBaseStatisitcVO
     * @return
     */
    List<StatisticsVo> staOrderBaseTotal(OrderBaseStatisitcVO orderBaseStatisitcVO);
}

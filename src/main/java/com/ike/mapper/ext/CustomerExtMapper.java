package com.ike.mapper.ext;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ike.pojo.Customer;
import com.ike.pojo.CustomerExample;
import com.ike.pojo.vo.*;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.function.LongFunction;

/**
 * @author: mokuanyuan
 * @since: 2019/10/15 8:21
 */
@Repository
public interface CustomerExtMapper {

    /**
     * Author: mokuanyuan
     *
     * @param page     分页参数
     * @param criteria 多重条件对象
     * @return Page
     * @apiNote: 根据多种条件查询客户信息列表
     * @since: 2019/10/12 20:28
     */
    IPage<CustomerListVo> selectListVoByCriteria(Page page, @Param("criteria") CustomerSearchVo criteria);

    /**
     * Author: mokuanyuan
     *
     * @param orderMinSum   订单的最小成交额
     * @param orderMaxSum   订单的最大成交额
     * @param orderMinCount 订单的最小成交次数
     * @param orderMaxCount 订单的最大成交次数
     * @return List<Long> 符合条件的客户id数组
     * @apiNote: 根据订单条件搜索符合的客户id
     * @since: 2019/10/13 19:42
     */
    List<CustomerVo> selectIdsByOrderBase(@Param("orderMinSum") Double orderMinSum,
                                          @Param("orderMaxSum") Double orderMaxSum,
                                          @Param("orderMinCount") Long orderMinCount,
                                          @Param("orderMaxCount") Long orderMaxCount);

    /**
     * Author: mokuanyuan
     *
     * @param complainMinCount 投诉的最小次数
     * @param complainMaxCount 订单的最大成交额
     * @return List<Long> 符合条件的客户id数组
     * @apiNote: 根据订单条件搜索符合的客户id
     * @since: 2019/10/13 19:42
     */
    List<CustomerVo> selectIdsByComplain(@Param("complainMinCount") Long complainMinCount, @Param("complainMaxCount") Long complainMaxCount);

    /**
     * Author: mokuanyuan
     *
     * @param followMinCount 最小跟进次数
     * @param followMaxCount 最大跟进次数
     * @param differFollow   多少天未跟进次数
     * @param isFollowed     是否是未跟进过
     * @return List<Long> 符合条件的客户id数组
     * @apiNote: 根据订单条件搜索符合的客户id
     * @since: 2019/10/14 22:21
     */
    List<CustomerVo> selectIdsByFollow(@Param("followMinCount") Long followMinCount,
                                       @Param("followMaxCount") Long followMaxCount,
                                       @Param("differFollow") Long differFollow,
                                       @Param("isFollowed") String isFollowed);

    /**
     * Author: mokuanyuan
     *
     * @param cid 客户id
     * @return CustomerVo
     * @apiNote: 根据客户id获取客户详情
     * @since: 2019/10/11 16:59
     */
    CustomerVo selectForUpdate(@Param("cid") Long cid);

    /**
     * Author: mokuanyuan
     *
     * @param id 客户id
     * @return CustomerDetailVo
     * @apiNote: 根据客户查询客户详情
     * @since: 2019/10/17 17:35
     */
    CustomerDetailVo selectDetail(@Param("id") Long id);


    /**
     * Author: mokuanyuan
     * @param cid 客户id
     * @return CustomerDetailVo
     * @apiNote: 查询客户的成交次数，成交总额，成交总产品数
     * @since: 2019/10/17 19:42
     */
    CustomerDetailVo selectOrderInfo(@Param("cid") Long cid);

    /**
     * Author: mokuanyuan
     *
     * @param cid 客户id
     * @return CustomerDetailVo
     * @apiNote: 查询客户的最近回款时间和总回款数
     * @since: 2019/10/21 9:34
     */
    CustomerDetailVo selectReturnInfo(@Param("cid") Long cid);


    /**
     * Author: mokuanyuan
     *
     * @param cid 客户id
     * @return CustomerDetailVo
     * @apiNote: 查询客户的退款次数和退款总额
     * @since: 2019/10/21 9:34
     */
    CustomerDetailVo selectRefundInfo(@Param("cid") Long cid);

    /**
     * Author: mokuanyuan
     *
     * @param cid 客户id
     * @return Long
     * @apiNote: 查询客户的投诉次数
     * @since: 2019/10/21 9:34
     */
    Long countComplain(@Param("cid") Long cid);

    /**
     * Author: mokuanyuan
     *
     * @return List<SelectVo>
     * @apiNote: 获取所有的客户id和客户名称
     * @since: 2019/10/21 11:19
     */
    List<SelectVo> selectIdAndNameAll();

    /**
     * Author: mokuanyuan
     *
     * @param list 客户列表
     * @return int 更新的记录数
     * @apiNote: 批量导入客户
     * @since: 2019/10/22 9:15
     */
    int importExcel(List<Customer> list);

}
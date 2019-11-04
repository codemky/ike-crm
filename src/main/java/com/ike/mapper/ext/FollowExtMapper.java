package com.ike.mapper.ext;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ike.pojo.vo.CustomerDetailVo;
import com.ike.pojo.vo.FollowSearchVo;
import com.ike.pojo.vo.FollowVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * InterfaceName FollowExtMapper
 * Description TODO
 *
 * @author mokuanyuan
 * @version 1.0
 * @date 2019/10/15 11:32
 **/
@Repository
public interface FollowExtMapper {

    /**
     * Author: mokuanyuan
     *
     * @param page     分页参数
     * @param criteria 多重条件对象
     * @return Page
     * @apiNote: 根据多种条件查询客户跟进列表
     * @since: 2019/10/15 15:48
     */
    IPage<FollowVo> selectByCriteria(Page page, @Param("criteria") FollowSearchVo criteria);

    /**
     * Author: mokuanyuan
     *
     * @param id 跟进记录id
     * @return FollowVo
     * @apiNote: 根据id获取跟进记录详情
     * @since: 2019/10/21 12:04
     */
    FollowVo selectDetailById(@Param("id") Long id);

    /**
     * Author: mokuanyuan
     *
     * @param cid 客户id
     * @return CustomerDetailVo
     * @apiNote: 查询客户的跟进次数，最近跟进时间
     * @since: 2019/10/17 19:42
     */
    CustomerDetailVo selectCustomerDetail(@Param("cid") Long cid);




}

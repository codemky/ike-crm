package com.ike.mapper.ext;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ike.pojo.FollowArrange;
import com.ike.pojo.vo.FollowArrangeSearchVo;
import com.ike.pojo.vo.FollowArrangeVo;
import com.ike.pojo.vo.FollowPlanSearchVo;
import com.ike.pojo.vo.FollowPlanVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * InterfaceName FollowArrangeExtMapper
 * Description TODO
 *
 * @author mokuanyuan
 * @version 1.0
 * @date 2019/10/16 8:07
 **/
@Repository
public interface FollowArrangeExtMapper {

    /**
     * Author: mokuanyuan
     *
     * @param page     分页参数
     * @param criteria 多重条件对象
     * @return Page
     * @apiNote: 根据多种条件查询客户跟进列表
     * @since: 2019/10/15 17:44
     */
    IPage<FollowArrangeVo> selectByCriteria(Page page, @Param("criteria") FollowArrangeSearchVo criteria);

    /**
     * Author: mokuanyuan
     *
     * @param cid 客户id
     * @return 客户跟进任务数量
     * @apiNote: 根据客户id获取客户跟进任务数量
     * @since: 2019/10/17 19:46
     */
    Long selectCustomerArrangeCount(@Param("cid") Long cid);


}

package com.ike.mapper.ext;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ike.pojo.vo.FollowPlanSearchVo;
import com.ike.pojo.vo.FollowPlanVo;
import com.ike.pojo.vo.FollowSearchVo;
import com.ike.pojo.vo.FollowVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * InterfaceName FollowPlanExtMapper.xml
 * Description TODO
 *
 * @author mokuanyuan
 * @version 1.0
 * @date 2019/10/15 17:43
 **/
@Repository
public interface FollowPlanExtMapper {

    /**
     * Author: mokuanyuan
     *
     * @param page     分页参数
     * @param criteria 多重条件对象
     * @return Page
     * @apiNote: 根据多种条件查询客户跟进列表
     * @since: 2019/10/15 17:44
     */
    IPage<FollowPlanVo> selectByCriteria(Page page, @Param("criteria") FollowPlanSearchVo criteria);

    /**
     * Author: mokuanyuan
     *
     * @param cid 客户id
     * @return 客户跟进计划数量
     * @apiNote: 根据客户id获取客户跟进计划数量
     * @since: 2019/10/17 19:46
     */
    Long selectCustomerPlanCount(@Param("cid") Long cid);

    /**
     * Author: mokuanyuan
     *
     * @param before 交接前员工id
     * @param after  交接后员工id
     * @return int 更新的记录数
     * @apiNote: 把前员工负责的所有客户转移到后员工上（计划表）
     * @since: 2019/10/21 20:36
     */
    int updateForTransferPlan(@Param("before") Long before, @Param("after") Long after);

    /**
     * Author: mokuanyuan
     *
     * @param before 交接前员工id
     * @param after  交接后员工id
     * @return int 更新的记录数
     * @apiNote: 把前员工负责的所有客户转移到后员工上（安排表中的安排人字段）
     * @since: 2019/10/21 20:36
     */
    int updateForTransferArrange(@Param("before") Long before, @Param("after") Long after);


    /**
     * Author: mokuanyuan
     *
     * @param before 交接前员工id
     * @param after  交接后员工id
     * @return int 更新的记录数
     * @apiNote: 把前员工负责的所有客户转移到后员工上（安排表中的负责人字段）
     * @since: 2019/10/21 20:36
     */
    int updateForTransferCharge(@Param("before") Long before, @Param("after") Long after);

}

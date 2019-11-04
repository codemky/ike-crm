package com.ike.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ike.pojo.FollowArrange;
import com.ike.pojo.vo.FollowArrangeSearchVo;
import com.ike.pojo.vo.FollowArrangeVo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * InterfaceName FollowArrangeService
 * Description TODO
 *
 * @author mokuanyuan
 * @version 1.0
 * @date 2019/10/16 9:11
 **/
@Repository
public interface FollowArrangeService {

    /**
     * Author: mokuanyuan
     *
     * @param page     分页参数
     * @param criteria 多重条件对象
     * @return Page
     * @apiNote: 根据多种条件查询客户跟进任务列表
     * @since: 2019/10/15 15:48
     */
    IPage<FollowArrangeVo> selectByCriteria(Page page, FollowArrangeSearchVo criteria);

    /**
     * Author: mokuanyuan
     *
     * @param followPlan 跟进计划记录实体
     * @return int 影响的记录数
     * @since: 2019/10/15 11:29
     */
    int create(FollowArrange followPlan);

    /**
     * Author: mokuanyuan
     *
     * @param followPlan 跟进计划记录实体
     * @return int 影响的记录数
     * @since: 2019/10/15 11:29
     */
    int update(FollowArrange followPlan);

    /**
     * Author: mokuanyuan
     *
     * @param id 跟进计划记录主键
     * @return int 影响的记录数
     * @since: 2019/10/15 11:29
     */
    int delete(Long id);

    /**
     * Author: mokuanyuan
     *
     * @param ids 跟进计划任务主键数组
     * @return int 影响的记录数
     * @since: 2019/10/15 11:29
     */
    int deleteByIds(List<Long> ids);

    /**
     * Author: mokuanyuan
     *
     * @param id 跟进计划记录主键
     * @return 跟进计划记录详情
     * @apiNote: 根据跟进计划记录id返回跟进计划记录实体
     * @since: 2019/10/15 16:41
     */
    FollowArrange detail(Long id);

    /**
     * Author: mokuanyuan
     *
     * @param id 跟进计划记录主键
     * @return 跟进计划记录详情
     * @apiNote: 根据跟进计划记录id返回跟进计划记录实体（id包含的名称也包含）
     * @since: 2019/10/15 16:41
     */
    FollowArrangeVo selectDetail(Long id);


    /**
     * Author: mokuanyuan
     *
     * @param ids 客户联系人主键数组
     * @return int 客户联系人关联的记录数
     * @apiNote: 获取某个客户联系人相关联的客户跟进任务数
     * @since: 2019/10/21 19:41
     */
    int countByRelationIds(List<Long> ids);

    /**
     * Author: mokuanyuan
     *
     * @param ids 客户联系人主键数组
     * @return int 受影响的记录数
     * @apiNote: 根据客户主键id删除所有相关的跟进任务
     * @since: 2019/10/21 19:42
     */
    int deleteByRelationIds(List<Long> ids);

    /**
     * Author: mokuanyuan
     *
     * @param before 交接前员工id
     * @param after  交接后员工id
     * @return int 受影响的记录数目
     * @apiNote: 把前员工负责的所有客户转移到后员工上
     * @since: 2019/10/21 19:44
     */
    int transferCustomer(Long before, Long after);


}

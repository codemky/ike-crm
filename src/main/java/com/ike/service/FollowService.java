package com.ike.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ike.pojo.Follow;
import com.ike.pojo.vo.FollowDetailVo;
import com.ike.pojo.vo.FollowSearchVo;
import com.ike.pojo.vo.FollowVo;
import org.apache.poi.ss.formula.functions.T;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * InterfaceName FollowService
 * Description TODO
 *
 * @author mokuanyuan
 * @version 1.0
 * @date 2019/10/15 11:29
 **/
public interface FollowService {

    /**
     * Author: mokuanyuan
     *
     * @param page     分页参数
     * @param searchVo 多种条件封装的对象
     * @return 客户跟进列表
     * @apiNote: 根据多种条件查询客户跟进
     * @since: 2019/10/15 16:06
     */
    IPage<FollowVo> selectByCriteria(Page page, FollowSearchVo searchVo);

    /**
     * Author: mokuanyuan
     *
     * @param follow 跟进记录实体
     * @return int 影响的记录数
     * @since: 2019/10/15 11:29
     */
    int create(Follow follow);

    /**
     * Author: mokuanyuan
     *
     * @param follow 跟进记录实体
     * @return int 影响的记录数
     * @since: 2019/10/15 11:29
     */
    int update(Follow follow);

    /**
     * Author: mokuanyuan
     *
     * @param id 跟进记录主键
     * @return int 影响的记录数
     * @since: 2019/10/15 11:29
     */
    int delete(Long id);

    /**
     * Author: mokuanyuan
     *
     * @param ids 跟进记录id数组
     * @return int 影响的跟进记录数
     * @apiNote: 批量删除跟进记录
     * @since: 2019/10/21 11:58
     */
    int deleteArrays(List<Long> ids);

    /**
     * Author: mokuanyuan
     *
     * @param id 跟进记录主键
     * @return 跟进记录详情
     * @apiNote: 根据跟进记录id返回跟进记录实体
     * @since: 2019/10/15 16:41
     */
    Follow detail(Long id);

    /**
     * Author: mokuanyuan
     *
     * @param id 跟进记录主键
     * @return 跟进记录详情
     * @apiNote: 根据跟进记录id返回跟进记录的详细记录
     * @since: 2019/10/21 14:38
     */
    FollowDetailVo selectDetailVo(Long id);

    /**
     * Author: mokuanyuan
     *
     * @param id 客户联系人主键id
     * @return int 客户联系人关联的记录数
     * @apiNote: 获取某个客户联系人相关联的客户跟进记录数
     * @since: 2019/10/21 19:41
     */
    int countByRelationId(Long id);

    /**
     * Author: mokuanyuan
     *
     * @param id 客户主键id
     * @return int 受影响的记录数
     * @apiNote: 根据客户主键id删除所有相关的跟进记录
     * @since: 2019/10/21 19:42
     */
    int deleteByCustomerId(Long id);

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

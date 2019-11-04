package com.ike.service;

import com.ike.pojo.ReturnPlanDetail;
import com.ike.pojo.vo.ReturnPlanVo;

import java.util.List;

public interface ReturnPlanDetailService {

    /**
     * 显示所有回款计划明细
     * @return
     */
    List<ReturnPlanDetail> selectAll();

    /**
     * 新增回款计划明细
     * @param returnPlanDetail
     * @return
     */
    int insert(ReturnPlanDetail returnPlanDetail);

    /**
     * 更新回款计划明细
     * @param returnPlanDetail
     * @return
     */
    int update(ReturnPlanDetail returnPlanDetail);

    /**
     * 根据主键id删除
     * @param id
     * @return
     */
    int deleteById(Long id);

    /**
     * 根据id选择获取回款计划明细
     * @param id
     * @return
     */
    ReturnPlanDetail selectById(Long id);

    List<ReturnPlanVo> listAll();

    /**
     * 根据计划表ID查询出该计划表下的所有计划明细和此回款计划的基本信息
    * @params [plan_id]
    * @Return java.util.List<com.ike.pojo.ReturnPlanDetail>
    * @Date 2019/10/12 1:21
    **/
    List<ReturnPlanVo> selectListByPlanId(Long plan_id);

    /**
    * 根据回款计划id和回款序号查询回款计划明细
    * @params [id, returnNumber]
    * @Return com.ike.pojo.ReturnPlanDetail
    * @Date 2019/10/12 1:49
    **/
    List<ReturnPlanDetail> selectByReturnPlanIdAndReturnNumber(Long return_plan_id, Integer returnNumber);

    /**
    * 根据计划表ID查询出该计划表下的所有计划明细数量
    * @params [returnPlanId]
    * @Return int
    * @Date 2019/10/12 2:01
    **/
    int countId(Long returnPlanId);

    /**
    * 根据id删除回款计划明细
    * @params [id]
    * @Return int
    * @Date 2019/10/12 3:19
    **/
    int deleteByKeyId(Long id);
}

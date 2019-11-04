package com.ike.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ike.pojo.ReturnPlan;

import java.util.List;

public interface ReturnPlanService {

    /**
     * 显示所有回款计划
     * @return
     */
    List<ReturnPlan> selectAll();

    /**
     * 新增订单回款计划
     * @param returnPlan
     * @return
     */
    int insert(ReturnPlan returnPlan);

    /**
     * 更新回款计划
     * @param returnPlan
     * @return
     */
    int update(ReturnPlan returnPlan);

    /**
     * 根据主键id删除
     * @param id
     * @return
     */
    int deleteById(Long id);

    /**
     * 根据id选择获取回款计划
     * @param id
     * @return
     */
    ReturnPlan selectById(Long id);

    /**
    * 根据id删除回款计划
    * @params [id]
    * @Return int
    * @Date 2019/10/12 2:56
    **/
    int deleteByKeyId(Long id);

    /**
     * 根据订单id查询回款计划和回款明细
     *
     * @params [id]
     * @Return com.ike.pojo.ReturnPlan
     * @Date 2019/10/18 23:08
     **/
    ReturnPlan findByOrderId(Long id);

    /**
     * 修改计划表和订单表之间的关联
     *
     * @params [old_plan_id, new_plan_id]
     * @Return int
     * @Date 2019/10/22 22:50
     **/
    int updateReturnPlanId(Long old_plan_id, Long new_plan_id);
}

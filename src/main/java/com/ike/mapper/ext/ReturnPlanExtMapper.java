package com.ike.mapper.ext;

import com.ike.pojo.ReturnPlan;
import com.ike.pojo.vo.ReturnPlanVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description TODO
 * @Date 2019/10/18 23:02
 */
public interface ReturnPlanExtMapper {
    ReturnPlan selectByOrderId(@Param("id") Long id);

    List<ReturnPlanVo> selectListByPlanId(@Param("plan_id") Long plan_id);

    List<ReturnPlanVo> selectListAll();
}

package com.ike.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ike.pojo.ReturnPlan;
import com.ike.pojo.ReturnPlanExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ReturnPlanMapper extends BaseMapper<ReturnPlan> {
    int countByExample(ReturnPlanExample example);

    int deleteByExample(ReturnPlanExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ReturnPlan record);

    int insertSelective(ReturnPlan record);

    List<ReturnPlan> selectByExample(ReturnPlanExample example);

    ReturnPlan selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ReturnPlan record, @Param("example") ReturnPlanExample example);

    int updateByExample(@Param("record") ReturnPlan record, @Param("example") ReturnPlanExample example);

    int updateByPrimaryKeySelective(ReturnPlan record);

    int updateByPrimaryKey(ReturnPlan record);
}
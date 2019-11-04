package com.ike.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ike.pojo.ReturnPlanDetail;
import com.ike.pojo.ReturnPlanDetailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ReturnPlanDetailMapper extends BaseMapper<ReturnPlanDetail> {
    int countByExample(ReturnPlanDetailExample example);

    int deleteByExample(ReturnPlanDetailExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ReturnPlanDetail record);

    int insertSelective(ReturnPlanDetail record);

    List<ReturnPlanDetail> selectByExample(ReturnPlanDetailExample example);

    ReturnPlanDetail selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ReturnPlanDetail record, @Param("example") ReturnPlanDetailExample example);

    int updateByExample(@Param("record") ReturnPlanDetail record, @Param("example") ReturnPlanDetailExample example);

    int updateByPrimaryKeySelective(ReturnPlanDetail record);

    int updateByPrimaryKey(ReturnPlanDetail record);
}
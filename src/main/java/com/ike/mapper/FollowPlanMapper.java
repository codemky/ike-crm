package com.ike.mapper;

import com.ike.pojo.FollowPlan;
import com.ike.pojo.FollowPlanExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FollowPlanMapper {
    int countByExample(FollowPlanExample example);

    int deleteByExample(FollowPlanExample example);

    int deleteByPrimaryKey(Long id);

    int insert(FollowPlan record);

    int insertSelective(FollowPlan record);

    List<FollowPlan> selectByExample(FollowPlanExample example);

    FollowPlan selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") FollowPlan record, @Param("example") FollowPlanExample example);

    int updateByExample(@Param("record") FollowPlan record, @Param("example") FollowPlanExample example);

    int updateByPrimaryKeySelective(FollowPlan record);

    int updateByPrimaryKey(FollowPlan record);
}
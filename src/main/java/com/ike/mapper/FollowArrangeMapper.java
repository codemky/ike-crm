package com.ike.mapper;

import com.ike.pojo.FollowArrange;
import com.ike.pojo.FollowArrangeExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FollowArrangeMapper {
    int countByExample(FollowArrangeExample example);

    int deleteByExample(FollowArrangeExample example);

    int deleteByPrimaryKey(Long id);

    int insert(FollowArrange record);

    int insertSelective(FollowArrange record);

    List<FollowArrange> selectByExample(FollowArrangeExample example);

    FollowArrange selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") FollowArrange record, @Param("example") FollowArrangeExample example);

    int updateByExample(@Param("record") FollowArrange record, @Param("example") FollowArrangeExample example);

    int updateByPrimaryKeySelective(FollowArrange record);

    int updateByPrimaryKey(FollowArrange record);
}
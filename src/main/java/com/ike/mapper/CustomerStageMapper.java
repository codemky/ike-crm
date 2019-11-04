package com.ike.mapper;

import com.ike.pojo.CustomerStage;
import com.ike.pojo.CustomerStageExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerStageMapper {
    int countByExample(CustomerStageExample example);

    int deleteByExample(CustomerStageExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CustomerStage record);

    int insertSelective(CustomerStage record);

    List<CustomerStage> selectByExample(CustomerStageExample example);

    CustomerStage selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CustomerStage record, @Param("example") CustomerStageExample example);

    int updateByExample(@Param("record") CustomerStage record, @Param("example") CustomerStageExample example);

    int updateByPrimaryKeySelective(CustomerStage record);

    int updateByPrimaryKey(CustomerStage record);
}
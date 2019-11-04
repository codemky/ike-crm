package com.ike.mapper;

import com.ike.pojo.CustomerStageLog;
import com.ike.pojo.CustomerStageLogExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CustomerStageLogMapper {
    int countByExample(CustomerStageLogExample example);

    int deleteByExample(CustomerStageLogExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CustomerStageLog record);

    int insertSelective(CustomerStageLog record);

    List<CustomerStageLog> selectByExample(CustomerStageLogExample example);

    CustomerStageLog selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CustomerStageLog record, @Param("example") CustomerStageLogExample example);

    int updateByExample(@Param("record") CustomerStageLog record, @Param("example") CustomerStageLogExample example);

    int updateByPrimaryKeySelective(CustomerStageLog record);

    int updateByPrimaryKey(CustomerStageLog record);
}
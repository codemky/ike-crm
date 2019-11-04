package com.ike.mapper;

import com.ike.pojo.CustomerLevel;
import com.ike.pojo.CustomerLevelExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerLevelMapper {
    int countByExample(CustomerLevelExample example);

    int deleteByExample(CustomerLevelExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CustomerLevel record);

    int insertSelective(CustomerLevel record);

    List<CustomerLevel> selectByExample(CustomerLevelExample example);

    CustomerLevel selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CustomerLevel record, @Param("example") CustomerLevelExample example);

    int updateByExample(@Param("record") CustomerLevel record, @Param("example") CustomerLevelExample example);

    int updateByPrimaryKeySelective(CustomerLevel record);

    int updateByPrimaryKey(CustomerLevel record);
}
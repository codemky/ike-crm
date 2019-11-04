package com.ike.mapper;

import com.ike.pojo.CustomerOrigin;
import com.ike.pojo.CustomerOriginExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerOriginMapper {
    int countByExample(CustomerOriginExample example);

    int deleteByExample(CustomerOriginExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CustomerOrigin record);

    int insertSelective(CustomerOrigin record);

    List<CustomerOrigin> selectByExample(CustomerOriginExample example);

    CustomerOrigin selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CustomerOrigin record, @Param("example") CustomerOriginExample example);

    int updateByExample(@Param("record") CustomerOrigin record, @Param("example") CustomerOriginExample example);

    int updateByPrimaryKeySelective(CustomerOrigin record);

    int updateByPrimaryKey(CustomerOrigin record);
}
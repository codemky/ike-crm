package com.ike.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ike.pojo.OrderBase;
import com.ike.pojo.OrderBaseExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderBaseMapper extends BaseMapper<OrderBase> {
    int countByExample(OrderBaseExample example);

    int deleteByExample(OrderBaseExample example);

    int deleteByPrimaryKey(Long id);

    int insert(OrderBase record);

    int insertSelective(OrderBase record);

    List<OrderBase> selectByExample(OrderBaseExample example);

    OrderBase selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") OrderBase record, @Param("example") OrderBaseExample example);

    int updateByExample(@Param("record") OrderBase record, @Param("example") OrderBaseExample example);

    int updateByPrimaryKeySelective(OrderBase record);

    int updateByPrimaryKey(OrderBase record);
}
package com.ike.mapper;

import com.ike.pojo.ProductClass;
import com.ike.pojo.ProductClassExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductClassMapper {
    int countByExample(ProductClassExample example);

    int deleteByExample(ProductClassExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ProductClass record);

    int insertSelective(ProductClass record);

    List<ProductClass> selectByExample(ProductClassExample example);

    ProductClass selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ProductClass record, @Param("example") ProductClassExample example);

    int updateByExample(@Param("record") ProductClass record, @Param("example") ProductClassExample example);

    int updateByPrimaryKeySelective(ProductClass record);

    int updateByPrimaryKey(ProductClass record);
}
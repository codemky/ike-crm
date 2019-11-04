package com.ike.mapper;

import com.ike.pojo.ProductFile;
import com.ike.pojo.ProductFileExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProductFileMapper {
    int countByExample(ProductFileExample example);

    int deleteByExample(ProductFileExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ProductFile record);

    int insertSelective(ProductFile record);

    List<ProductFile> selectByExample(ProductFileExample example);

    ProductFile selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ProductFile record, @Param("example") ProductFileExample example);

    int updateByExample(@Param("record") ProductFile record, @Param("example") ProductFileExample example);

    int updateByPrimaryKeySelective(ProductFile record);

    int updateByPrimaryKey(ProductFile record);
}
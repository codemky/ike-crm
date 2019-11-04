package com.ike.mapper;

import com.ike.pojo.SaleUnit;
import com.ike.pojo.SaleUnitExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SaleUnitMapper {
    int countByExample(SaleUnitExample example);

    int deleteByExample(SaleUnitExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SaleUnit record);

    int insertSelective(SaleUnit record);

    List<SaleUnit> selectByExample(SaleUnitExample example);

    SaleUnit selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SaleUnit record, @Param("example") SaleUnitExample example);

    int updateByExample(@Param("record") SaleUnit record, @Param("example") SaleUnitExample example);

    int updateByPrimaryKeySelective(SaleUnit record);

    int updateByPrimaryKey(SaleUnit record);
}
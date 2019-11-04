package com.ike.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ike.pojo.ReturnDetail;
import com.ike.pojo.ReturnDetailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ReturnDetailMapper extends BaseMapper<ReturnDetail> {
    int countByExample(ReturnDetailExample example);

    int deleteByExample(ReturnDetailExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ReturnDetail record);

    int insertSelective(ReturnDetail record);

    List<ReturnDetail> selectByExample(ReturnDetailExample example);

    ReturnDetail selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ReturnDetail record, @Param("example") ReturnDetailExample example);

    int updateByExample(@Param("record") ReturnDetail record, @Param("example") ReturnDetailExample example);

    int updateByPrimaryKeySelective(ReturnDetail record);

    int updateByPrimaryKey(ReturnDetail record);
}
package com.ike.mapper;

import com.ike.pojo.ComplaintHandle;
import com.ike.pojo.ComplaintHandleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

@Repository
public interface ComplaintHandleMapper {
    int countByExample(ComplaintHandleExample example);

    int deleteByExample(ComplaintHandleExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ComplaintHandle record);

    int insertSelective(ComplaintHandle record);

    List<ComplaintHandle> selectByExample(ComplaintHandleExample example);

    ComplaintHandle selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ComplaintHandle record, @Param("example") ComplaintHandleExample example);

    int updateByExample(@Param("record") ComplaintHandle record, @Param("example") ComplaintHandleExample example);

    int updateByPrimaryKeySelective(ComplaintHandle record);

    int updateByPrimaryKey(ComplaintHandle record);
}

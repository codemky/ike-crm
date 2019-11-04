package com.ike.mapper.ext;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ike.pojo.CustomerStageLog;
import com.ike.pojo.CustomerStageLogExample;
import com.ike.pojo.vo.CustomerStageLogVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerStageLogExtMapper {

    IPage<CustomerStageLogVo> selectByCustomerId(Page page, @Param("customerId") Long customerId);
}
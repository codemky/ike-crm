package com.ike.mapper.ext;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ike.pojo.vo.ComplaintSearchVo;
import com.ike.pojo.vo.ComplaintVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComplaintExtMapper {

    IPage<ComplaintVo> selAll(Page page);

    IPage<ComplaintVo> selUnHandle(Page page);

    IPage<ComplaintVo> selIsHandled(Page page);

    IPage<ComplaintVo> selHandleByCurUser(Page page, @Param("curUserId") Long curUserId);

    IPage<ComplaintVo> advanceSearch(Page<ComplaintVo> page,
                                     @Param("complaintSearchVo") ComplaintSearchVo complaintSearchVo);

    /**
     * 查询与客户相关的投诉记录
     */
    List<Long> selByCustomerId(@Param("customerId") Long customerId);

}

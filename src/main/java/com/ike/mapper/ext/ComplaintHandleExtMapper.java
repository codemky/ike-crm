package com.ike.mapper.ext;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ike.pojo.vo.ComplaintHandleVo;
import com.ike.pojo.vo.ComplaintVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComplaintHandleExtMapper {

    List<ComplaintHandleVo> selAllByComplaintId(@Param("complaintId") Long complaintId);

}

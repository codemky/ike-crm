package com.ike.mapper.ext;

import com.ike.pojo.vo.RefundExportVo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Date: 2019-10-22 23:11
 * @Description:(描述)
 */
@Repository
public interface RefundExportExtMapper {

    List<RefundExportVo> selectAllToExport();

}

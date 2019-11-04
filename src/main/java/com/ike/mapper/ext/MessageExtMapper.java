package com.ike.mapper.ext;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ike.pojo.vo.MessageVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageExtMapper {

    IPage<MessageVo> selAllByCurUserId(Page page, @Param("curUserId") Long curUserId);

    IPage<MessageVo> selUnReadByCurUserId(Page page, @Param("curUserId") Long curUserId);

    IPage<MessageVo> selIsReadByCurUserId(Page page, @Param("curUserId") Long curUserId);
}

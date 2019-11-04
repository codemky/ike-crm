package com.ike.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ike.common.result.Result;
import com.ike.pojo.Message;
import com.ike.pojo.vo.MessageVo;

import java.util.List;

public interface MessageService {

    /**
     * 获取当前用户的全部消息
     */
    IPage<MessageVo> selAllByCurUserId(Page page, Long curUserId);

    /**
     * 获取当前用户的未读消息
     */
    IPage<MessageVo> selUnReadCurUserId(Page page, Long curUserId);

    /**
     * 获取当前用户的已读消息
     */
    IPage<MessageVo> selIsReadByCurUserId(Page page, Long curUserId);

    /**
     * 删除指定id的消息记录
     */
    int deleteById(Long id);

    /**
     * 批量删除指定id的消息记录
     */
    int batchDelete(List<Long> ids);

    /**
     * 批量置为已读 指定id的消息记录
     */
    int batchToRead(List<Long> ids);

    /**
     * 批量置为未读 指定id的消息记录
     */
    int batchToUnRead(List<Long> ids);

    /**
     * 根据用户id删除与其所有相关的记录
     */
    int deleteAboutUserId(Long userId);

    /**
     * 发送一条内部信
     */
    int sendMessage(Message message);
}

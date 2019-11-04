package com.ike.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ike.mapper.MessageMapper;
import com.ike.mapper.ext.MessageExtMapper;
import com.ike.pojo.Message;
import com.ike.pojo.MessageExample;
import com.ike.pojo.vo.MessageVo;
import com.ike.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageMapper messageMapper;

    @Autowired
    private MessageExtMapper extMapper;

    @Override
    public IPage<MessageVo> selAllByCurUserId(Page page, Long curUserId) {
        return extMapper.selAllByCurUserId(page, curUserId);
    }

    @Override
    public IPage<MessageVo> selUnReadCurUserId(Page page, Long curUserId) {
        return extMapper.selUnReadByCurUserId(page, curUserId);
    }

    @Override
    public IPage<MessageVo> selIsReadByCurUserId(Page page, Long curUserId) {
        return extMapper.selIsReadByCurUserId(page, curUserId);
    }


    @Override
    public int deleteById(Long id) {
        MessageExample example = new MessageExample();
        example.createCriteria().andIdEqualTo(id);
        return messageMapper.deleteByExample(example);
    }

    @Override
    public int batchDelete(List<Long> ids) {
        MessageExample example = new MessageExample();
        example.createCriteria().andIdIn(ids);
        return messageMapper.deleteByExample(example);
    }

    @Override
    public int batchToRead(List<Long> ids) {
        Message message = new Message();
        message.setReaded(((byte) 1));
        message.setReadTime(LocalDateTime.now());
        MessageExample example = new MessageExample();
        example.createCriteria().andIdIn(ids);
        return messageMapper.updateByExampleSelective(message, example);
    }

    @Override
    public int batchToUnRead(List<Long> ids) {
        Message message = new Message();
        message.setReaded(((byte) 0));
        message.setReadTime(LocalDateTime.now());
        MessageExample example = new MessageExample();
        example.createCriteria().andIdIn(ids);
        return messageMapper.updateByExampleSelective(message, example);
    }

    @Override
    public int deleteAboutUserId(Long userId) {
        MessageExample example = new MessageExample();
        example.or().andToIdEqualTo(userId);
        example.or().andFromIdEqualTo(userId);
        return messageMapper.deleteByExample(example);
    }

    @Override
    public int sendMessage(Message message) {
        return messageMapper.insert(message);
    }
}

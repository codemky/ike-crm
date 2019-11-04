package com.ike.mq;

import com.alibaba.fastjson.JSON;
import com.ike.common.constans.CodeMsg;
import com.ike.common.exception.IKEException;
import com.ike.pojo.Message;
import com.ike.service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author wgm
 * @Date 2019/10/6 10:26
 */
@Service
public class MQReceiver {

    private static Logger logger = LoggerFactory.getLogger(MQReceiver.class);

    @Autowired
    private MessageService messageService;

    /**
     * 监听内部消息队列
     */
    @RabbitListener(queues = MQConfig.INNER_MSG)
    public void receiveInnerMsgQueue(String msg) {
        logger.info("接受内部消息-" + msg);
        Message message = null;
        try {
            message = JSON.parseObject(msg, Message.class);
        } catch ( Exception e ) {
            throw new IKEException(CodeMsg.JSON_PARSE_ERROR);
        }
        messageService.sendMessage(message);
    }
}

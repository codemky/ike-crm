package com.ike.mq;

import com.alibaba.fastjson.JSON;
import com.ike.common.constans.CodeMsg;
import com.ike.common.exception.IKEException;
import com.ike.pojo.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author wgm
 * @Date 2019/10/6 10:26
 */
@Service
public class MQSender {

    private static Logger logger = LoggerFactory.getLogger(MQSender.class);

    @Autowired
    AmqpTemplate amqpTemplate;

    /**
     * 发送消息
     */
    public void sendMsgMessage(Message message) {
        logger.info("发送内部消息: " + message.getMsgTitle());
        String msg = "";
        try {
            msg = JSON.toJSONString(message);
        } catch ( Exception e ) {
            throw new IKEException(CodeMsg.JSON_PARSE_ERROR);
        }
        amqpTemplate.convertAndSend(MQConfig.INNER_MSG, msg);
    }

//    public static void main(String[] args) {
//        Message message = new Message();
//        message.setFromId(1L);
//        message.setToId(1L);
//        message.setMsgTitle("消息队列测试标题");
//        message.setMsgContent("消息队列测试内容");
//        message.setReaded(((byte) 0));
//        message.setSendTime(LocalDateTime.now());
//        String msg = JSON.toJSONString(message);
//        Message message1 = JSON.parseObject(msg, Message.class);
//        System.out.println(message1.getMsgContent());
//        System.out.println(msg);
//    }
}

package com.ike.mq;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author wgm
 * @Date 2019/10/6 10:26
 */
@Configuration
public class MQConfig {

    public static final String INNER_MSG = "inner_msg";

    /**
     * 内部消息队列
     */
    @Bean
    public Queue msgQueue() {
        return new Queue(INNER_MSG, true);
    }

}

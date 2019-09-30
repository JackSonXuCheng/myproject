package com.myproject.mq.action;

import com.myproject.mq.config.RabbitmqConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * @author jackson
 * @version 1.0
 * @date 2019/9/30 14:42
 * @comment:消息发送者
 */
@Slf4j
@Component
public class Sender {

    @Autowired
    private AmqpTemplate amqpTemplate;

    public String send() {
        String hello = "hello 测试数据 hello";
        log.info("消息发送时间" + System.currentTimeMillis());
        amqpTemplate.convertAndSend("message", hello);
        return "success";
    }

    public void sendTopic() {
        String helloTopic = "hello Topic";
        log.info("helloTopic发送：" + System.currentTimeMillis());
        amqpTemplate.convertAndSend("topicQueue", RabbitmqConfig.ROUTING_KEY1, helloTopic);

    }
}

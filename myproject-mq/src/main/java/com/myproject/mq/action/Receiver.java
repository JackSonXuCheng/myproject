package com.myproject.mq.action;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author jackson
 * @version 1.0
 * @date 2019/9/30 14:45
 * @comment:消息接受者
 */
@Slf4j
@Component
public class Receiver {

    @RabbitListener(queues = "message")
    public void process(String str) {

        log.info("接收消息" + str);

    }

    @RabbitListener(queues = "topicQueue")
    public void receiveTopic(String message, String s) {
        log.info("接收订阅信息" + message);

    }
}

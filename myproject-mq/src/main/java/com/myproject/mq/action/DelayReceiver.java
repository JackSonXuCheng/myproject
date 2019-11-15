package com.myproject.mq.action;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
 * @author jackson
 * @version 1.0
 * @date 2019/11/14 11:54
 * @comment:延时消息发送队列
 */
@Component
@Slf4j
public class DelayReceiver {

    @RabbitListener(queues = "delayQueue")
    public void delayReceiver(String msg) {
        log.info("消息接收时间：" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        System.out.println(msg);
    }


}

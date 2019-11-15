package com.myproject.mq.action;

import com.myproject.mq.config.RabbitmqConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

/**
 * @author jackson
 * @version 1.0
 * @date 2019/11/14 12:00
 * @comment:延时消息发送方
 */
@Component
@Slf4j
public class DelaySender {

    @Autowired
    private RabbitTemplate rabbitTemplate;


    public void delaySender() {
        log.info("消息发送时间：" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        rabbitTemplate.convertAndSend("test_exchange", RabbitmqConfig.DELAY_KEY, "延时信息发送成功", message -> {
            message.getMessageProperties().setHeader("x-delay", TimeUnit.SECONDS.toSeconds(6000));
            return message;
        });
    }


}

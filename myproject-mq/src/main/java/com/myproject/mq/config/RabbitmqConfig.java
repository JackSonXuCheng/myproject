package com.myproject.mq.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;


/**
 * @author jackson
 * @version 1.0
 * @date 2019/9/30 11:27
 * @comment:
 */
@Configuration
@Slf4j
public class RabbitmqConfig {

    public static final String ROUTING_KEY1 = "topic.key1";

    public static final String DELAY_KEY = "delay.key";

    @Bean("queue")
    public Queue createQueue() {
        return new Queue("message");
    }

    @Bean(name = "topicQueue")
    public Queue topicQueue() {
        return new Queue("topicQueue");
    }

    @Bean
    public TopicExchange topicExchange() {

        return new TopicExchange("topicQueue");

    }

    @Bean
    public Binding topicBind1(Queue topicQueue, TopicExchange topicExchange) {

        return BindingBuilder.bind(topicQueue).to(topicExchange).with(ROUTING_KEY1);

    }

    /**
     * 延时消息队列
     *
     * @return
     */

    @Bean("delayQueue")
    public Queue delayQueue() {
        return new Queue("delayQueue");
    }


    @Bean
    public CustomExchange delayExchange() {
        Map<String, Object> map = new HashMap<>(1);
        map.put("x-delayed-type", "direct");

        return new CustomExchange("test_exchange", "x-delayed-message", true, false, map);
    }

    @Bean
    public Binding delayBinding(Queue delayQueue, CustomExchange customExchange) {
        return BindingBuilder.bind(delayQueue).to(customExchange).with(DELAY_KEY).noargs();
    }
}

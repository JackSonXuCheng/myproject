package com.myproject.mq.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


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

}

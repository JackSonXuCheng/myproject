package com.myproject.console.test;

import com.myproject.mq.action.DelaySender;
import com.myproject.mq.action.Sender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jackson
 * @version 1.0
 * @date 2019/9/30 15:00
 * @comment:
 */
@RestController
@RequestMapping("api/test/exclude/")
public class RabbitMqController {

    @Autowired
    private Sender sender;

    @Autowired
    private DelaySender delaySender;
    @GetMapping("hello")
    public String hello() {
        sender.send();
        sender.sendTopic();
        return "success";
    }

    @GetMapping("delayHello")
    public String delayHello() {

        delaySender.delaySender();
        return "success";
    }
}

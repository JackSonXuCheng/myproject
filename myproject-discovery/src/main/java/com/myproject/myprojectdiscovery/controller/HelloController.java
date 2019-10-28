package com.myproject.myprojectdiscovery.controller;

import com.myproject.mq.action.Sender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jackson
 * @version 1.0
 * @date 2019/10/22 17:25
 * @comment:
 */
@RestController
@RequestMapping("discovery/hello")
public class HelloController {

    @Autowired
    private Sender sender;

    @GetMapping("hello")
    public String hello() {
        sender.sendTopic();
        return "hello";
    }


}

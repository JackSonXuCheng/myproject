package com.myproject.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * RefreshScope注解 修改nacos配置文件  配置文件自动刷新
 *
 * @author jackson
 * @version 1.0
 * @date 2019/10/31 11:14
 * @comment:
 */
@RestController
@RequestMapping("/nacosConfig")
@RefreshScope
public class NacosConfigTestController {
    @Value("${profile}")
    private String profile;

    @GetMapping("/profile")
    public String hello() {

        return this.profile;
    }
}

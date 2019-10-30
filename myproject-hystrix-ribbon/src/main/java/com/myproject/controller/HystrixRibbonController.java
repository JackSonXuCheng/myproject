package com.myproject.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author jackson
 * @version 1.0
 * @date 2019/10/28 16:57
 * @comment:
 */
@RestController
@RequestMapping("/htstrixRibbon")
@Slf4j
public class HystrixRibbonController {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "findByIdFallBack")
    @GetMapping("findById")
    public String findById() {
        return String.valueOf(0 / 0);
    }

    public String findByIdFallBack(Throwable throwable) {
        log.info("findByIdFallBack", throwable);
        return "he";
    }
}

package com.myproject.myprojectsecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jackson
 * @version 1.0
 * @date 2019/11/2 10:26
 * @comment:
 */
@RestController
public class HelloController {

    @GetMapping("hello")
    public String hello() {

        return "hello";
    }

    @GetMapping("helloTest")
    public String helloTest() {
        return "helloTest";
    }


}

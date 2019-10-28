package com.myproject.ribbon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author jackson
 * @version 1.0
 * @date 2019/10/25 11:55
 * @comment:
 */
@RestController
@RequestMapping("/ribbon")
public class RibbonController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/users/{id}")
    public String findById(@PathVariable(name = "id") Long id) {
        String result = this.restTemplate.getForObject("http://myproject-discovery/discovery/hello/hello", String
                .class);
        return result;
    }

}

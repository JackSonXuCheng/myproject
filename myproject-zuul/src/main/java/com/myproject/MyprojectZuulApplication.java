package com.myproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @author jackson
 */
@SpringBootApplication
@EnableZuulProxy
public class MyprojectZuulApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyprojectZuulApplication.class, args);
    }

}

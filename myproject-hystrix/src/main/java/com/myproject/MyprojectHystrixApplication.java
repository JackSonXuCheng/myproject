package com.myproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * EnableCircuitBreaker注解配置Hystrix
 * @author jackson
 */
@EnableCircuitBreaker
@SpringBootApplication
@EnableHystrixDashboard
public class MyprojectHystrixApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyprojectHystrixApplication.class, args);
    }

}

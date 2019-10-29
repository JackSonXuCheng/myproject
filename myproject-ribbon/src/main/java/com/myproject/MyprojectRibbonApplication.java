package com.myproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author jackson
 */
@SpringBootApplication
@EnableEurekaClient
public class MyprojectRibbonApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyprojectRibbonApplication.class, args);
    }

    @Bean
    /**
     * 必须要此注解 才能整合RestTemplate整合Ribbon！
     */
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}

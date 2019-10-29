package com.myproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MyprojectDiscoveryApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyprojectDiscoveryApplication.class, args);
    }

}

package com.myproject.myprojectalibabanacosserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author jacksobn
 */
@SpringBootApplication
@EnableDiscoveryClient
public class MyprojectAlibabaNacosServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyprojectAlibabaNacosServerApplication.class, args);
    }

}

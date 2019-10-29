package com.myproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author jackson
 */
@SpringBootApplication
@EnableFeignClients
public class MyprojectFeignApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyprojectFeignApplication.class, args);
    }


}

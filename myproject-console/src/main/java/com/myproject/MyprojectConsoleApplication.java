package com.myproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages = {"com.gitee.sunchenbin.mybatis.actable.dao.*"})
@ComponentScans(@ComponentScan("com.gitee.sunchenbin.mybatis.actable.manager.*"))
public class MyprojectConsoleApplication {


    public static void main(String[] args) {
        SpringApplication.run(MyprojectConsoleApplication.class, args);
    }

}

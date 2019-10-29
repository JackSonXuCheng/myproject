package com.myproject.hystrixfeign.controller;

import com.myproject.hystrixfeign.entity.User;
import com.myproject.hystrixfeign.feign.UserFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jackson
 * @version 1.0
 * @date 2019/10/29 10:33
 * @comment:
 */
@RestController
@RequestMapping("hystrixFeign")
public class UserController {

    @Autowired
    UserFeignClient userFeignClient;


    @GetMapping("findById/{id}")
    public User findById(@PathVariable(name = "id") Long id) {
        return userFeignClient.findById(id);
    }

}

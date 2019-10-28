package com.myproject.controller;

import com.myproject.api.FeignServiceApi;
import com.myproject.feign.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jackson
 * @version 1.0
 * @date 2019/10/28 11:22
 * @comment:
 */
@RestController
@RequestMapping("api/feignController")
public class FeignController {

    @Autowired
    private FeignServiceApi feignServiceApi;


    @GetMapping("/users/{id}")
    public User findByUserId(@PathVariable(name = "id") Long id) {

        return feignServiceApi.findByUserId(id);
    }

}

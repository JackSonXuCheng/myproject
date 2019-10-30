package com.myproject.controller;

import com.myproject.api.FeignServiceApi;
import com.myproject.feign.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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


    @GetMapping("/users")
    public User findUser(@RequestBody User user) {
        user = new User();
        return feignServiceApi.findUser(user);
    }

}

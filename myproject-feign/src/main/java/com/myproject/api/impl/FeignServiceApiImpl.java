package com.myproject.api.impl;

import com.myproject.api.FeignServiceApi;
import com.myproject.feign.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jackson
 * @version 1.0
 * @date 2019/10/28 11:30
 * @comment:
 */
@Service
@RestController
public class FeignServiceApiImpl implements FeignServiceApi {
    @Override
    public User findByUserId(Long id) {
        User user = new User();
        user.setAge(12);
        return user;
    }
}

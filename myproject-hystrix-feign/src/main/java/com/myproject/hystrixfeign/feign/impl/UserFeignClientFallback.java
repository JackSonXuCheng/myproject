package com.myproject.hystrixfeign.feign.impl;

import com.myproject.hystrixfeign.entity.User;
import com.myproject.hystrixfeign.feign.UserFeignClient;
import org.springframework.stereotype.Component;

/**
 * @author jackson
 * @version 1.0
 * @date 2019/10/29 10:32
 * @comment:
 */
@Component
public class UserFeignClientFallback implements UserFeignClient {
    @Override
    public User findById(Long id) {
        User user = new User();
        user.setAge(12);
        return user;
    }
}

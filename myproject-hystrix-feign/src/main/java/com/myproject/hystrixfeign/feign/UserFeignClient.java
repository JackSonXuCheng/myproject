package com.myproject.hystrixfeign.feign;

import com.myproject.hystrixfeign.entity.User;
import com.myproject.hystrixfeign.feign.impl.UserFeignClientFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author jackson
 * @version 1.0
 * @date 2019/10/29 10:30
 * @comment:
 */
@FeignClient(name = "myproject-hystrix-feign", fallback = UserFeignClientFallback.class)
public interface UserFeignClient {

    @GetMapping("/users/{id}")
    User findById(@PathVariable(name = "id") Long id);


}

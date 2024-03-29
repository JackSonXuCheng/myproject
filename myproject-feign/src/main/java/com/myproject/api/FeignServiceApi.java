package com.myproject.api;

import com.myproject.feign.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


/**
 * @author jackson
 * @version 1.0
 * @date 2019/10/28 11:10
 * @comment: feign 测试
 */
// name值必须跟spring.boot.name 一致

@FeignClient(name = "myproject-feign")
public interface FeignServiceApi {
    /**
     * 通过用户查询用户Id
     *
     * @param id 用户Id
     * @return
     */
    @GetMapping("/users/{id}")
    User findByUserId(@PathVariable("id") Long id);

    @GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    User findUser(User user);

}

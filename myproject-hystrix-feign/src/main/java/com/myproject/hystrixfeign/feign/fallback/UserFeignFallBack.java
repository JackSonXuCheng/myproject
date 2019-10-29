package com.myproject.hystrixfeign.feign.fallback;

import com.myproject.hystrixfeign.feign.UserFeignClient;
import com.myproject.hystrixfeign.feign.impl.UserFeignClientFallback;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author jackson
 * @version 1.0
 * @date 2019/10/29 10:49
 * @comment: Feign全局启用hystrix 获取回滚原因
 */
@Slf4j
@Component
public class UserFeignFallBack implements FallbackFactory<UserFeignClient> {
    @Autowired
    UserFeignClientFallback userFeignClientFallback;

    @Override
    public UserFeignClient create(Throwable throwable) {
        log.warn("回滚原因", throwable);
        return userFeignClientFallback;
    }
}

package com.myproject.config.interceptor_config;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author jackson
 * @version 1.0
 * @date 2019/9/24 17:19
 * @comment:
 */
@Component
@Slf4j
public class MemberInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws
            Exception {
        log.warn("前端请求参数：" + JSON.toJSONString(request.getParameterMap()));
        log.warn("请求类型：" + request.getContentType());


        return false;
    }
}

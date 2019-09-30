package com.myproject.config.interceptor_config;

import com.alibaba.fastjson.JSON;
import com.myproject.common.Base.Result;
import com.myproject.config.xss_config.XssHttpServletRequestWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

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
        XssHttpServletRequestWrapper xssHttpServletRequestWrapper = new XssHttpServletRequestWrapper(request, true);
        String username = xssHttpServletRequestWrapper.getParameter("username");
        String randomKey = xssHttpServletRequestWrapper.getParameter("randomKey");
        if (StringUtils.isBlank(username) || StringUtils.isBlank(randomKey)) {
            Result result = Result.invalid("username or randomKey is blank", null);
            PrintWriter printWriter = response.getWriter();
            printWriter.write(JSON.toJSONString(result));
            printWriter.flush();
            printWriter.close();
            return false;
        }

        //TODO:用户登录判断
        return true;
    }
}

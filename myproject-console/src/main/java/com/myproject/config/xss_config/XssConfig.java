package com.myproject.config.xss_config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.HashMap;
import java.util.Map;

/**
 * @author jackson
 * @version 1.0
 * @date 2019/9/23 16:26
 * @comment:
 */
@Configuration
public class XssConfig implements WebMvcConfigurer {
    @Autowired
    private XssFilter xssFilter;

    @Bean
    public FilterRegistrationBean registerAuthFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();

        registration.setFilter(xssFilter);
        registration.setName("authFilter");
        registration.addUrlPatterns("/*");
        Map<String, String> initParameters = new HashMap<>();
        initParameters.put("excludes", "/favicon.ico,/img/*,/js/*,/css/*");
        initParameters.put("isIncludeRichText", "true");
        registration.setOrder(1);
        return registration;
    }


}

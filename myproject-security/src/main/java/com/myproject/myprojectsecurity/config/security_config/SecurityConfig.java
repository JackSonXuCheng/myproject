package com.myproject.myprojectsecurity.config.security_config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author jackson
 * @version 1.0
 * @date 2019/11/2 10:32
 * @comment: security 配置
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 密码加密
     *
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 权限配置
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/").permitAll()
                .antMatchers("/hello/**").hasRole("admin")
                .antMatchers("/helloTest/**").hasRole("test");
        //配置登陆功能
        http.formLogin().usernameParameter("user")
                .passwordParameter("pwd");
        //注销成功跳转到首页
        http.logout().logoutSuccessUrl("/");
        //开启记住我功能
        http.rememberMe().rememberMeParameter("remember");
    }


}

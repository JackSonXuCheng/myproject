package com.myproject.config.shiro_config;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.session.mgt.DefaultSessionManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

/**
 * @author jackson
 * @version 1.0
 * @date 2019/9/5 14:44
 * @comment: shiro相关配置
 */
@Configuration
@Slf4j
public class ShiroConfig {


    /**
     * shiro基础配置
     *
     * @return
     */
    @Bean
    public ShiroFilterChainDefinition shiroFilterChainDefinition() {
        DefaultShiroFilterChainDefinition chain = new DefaultShiroFilterChainDefinition();
        /**
         * anon: 指定url可以匿名访问
         * authc：有url都必须认证通过才可以访问 指定url需要form表单登录，默认会从请求中获取username、password,
         * rememberMe等参数并尝试登录，如果登录不了就会跳转到loginUrl配置的路径。我们也可以用这个过滤器做默认的登录逻辑，但是一般都是我们自己在控制器写登录逻辑的，自己写的话出错返回的信息都可以定制嘛。
         * logout:登出过滤器，配置指定url就可以实现退出功能，非常方便
         * perms：需要指定权限才能访问
         */
        chain.addPathDefinition("/css/**", "anon");
        chain.addPathDefinition("/img/**", "anon");
        chain.addPathDefinition("/js/**", "anon");
        chain.addPathDefinition("/lib/**", "anon");

        chain.addPathDefinition("/console/logout", "logout");

        //管理员管理
        chain.addPathDefinition("/admin/**", "perms[admin:admin]");
        //角色管理
        chain.addPathDefinition("/role/**", "perms[admin:role]");


        //必須放到最后
        chain.addPathDefinition("/**", "authc");
        return chain;
    }

    /**
     * shiro中redis作为缓存，redis相关配置
     *
     * @param redisProperties
     * @return
     */
    @Bean
    public RedisManager redisManager(RedisProperties redisProperties) {
        RedisManager redisManager = new RedisManager();
        redisManager.setHost(redisProperties.getHost());
        redisManager.setPassword(redisProperties.getPassword());
        redisManager.setDatabase(redisProperties.getDatabase());
        Duration timeout = redisProperties.getTimeout();
        if (timeout != null) {
            Number t = timeout.toMillis();
            redisManager.setTimeout(t.intValue());
        }
        if (redisProperties.getPassword() != null && redisProperties.getPassword().length() > 0) {
            redisManager.setPassword(redisProperties.getPassword());
        }
        return redisManager;
    }

    /**
     * 配置RedisSessionDAO
     *
     * @param redisManager
     * @return
     */
    @Bean
    public RedisSessionDAO redisSessionDAO(RedisManager redisManager) {
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        redisSessionDAO.setRedisManager(redisManager);
        return redisSessionDAO;
    }

    @Bean
    public SessionManager sessionManager(RedisSessionDAO redisSessionDAO) {
        DefaultSessionManager sessionManager = new DefaultSessionManager();

        return sessionManager;
    }
}

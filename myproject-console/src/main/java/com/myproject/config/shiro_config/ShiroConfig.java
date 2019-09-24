package com.myproject.config.shiro_config;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
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


    @Value("${myproject-session-timeout:3000}")
    private Integer sessionTimeOut;


    @Value("${myproject-session-validate:1800}")
    private Integer sessionValidate;

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
        chain.addPathDefinition("/images/**", "anon");
        chain.addPathDefinition("/layui/**", "anon");
        chain.addPathDefinition("/json/**", "anon");
        chain.addPathDefinition("/console/img/getKaptchaImage", "anon");
        chain.addPathDefinition("/console/login", "anon");
        chain.addPathDefinition("/console/logout", "logout");

        //管理员管理
        chain.addPathDefinition("/console/admin/**", "perms[admin:admin]");
        //角色管理
        chain.addPathDefinition("/console/role/**", "perms[admin:role]");


        //必須放到最后
        chain.addPathDefinition("/**", "authc");
        return chain;
    }

    /**
     * shiro中redis作为缓存，redis相关配置
     * @param redisProperties
     * @return
     */
    @Bean
    public RedisManager redisManager(RedisProperties redisProperties) {
        RedisManager redisManager = new RedisManager();
        redisManager.setHost(redisProperties.getHost());
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

    /**
     * 配置sessionManager
     *
     * @param redisSessionDAO
     * @return
     */
    @Bean
    public SessionManager sessionManager(RedisSessionDAO redisSessionDAO) {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setSessionDAO(redisSessionDAO);
        //设置全局session结束时间 单位：毫秒
        sessionManager.setGlobalSessionTimeout(sessionTimeOut * 1000);
        //会话验证间隔时间 单位:毫秒
        sessionManager.setSessionValidationInterval(sessionValidate * 1000);
        //删除无效会话
        sessionManager.setDeleteInvalidSessions(true);
        //验证会话
        sessionManager.validateSessions();
        //清理用户直接关闭浏览器造成的孤立会话
        sessionManager.setSessionIdUrlRewritingEnabled(false);
        return sessionManager;
    }

    /**
     * 配置cache管理器
     * 用于往redsi存储权限和角色标识
     *
     * @param redisManager
     * @return
     */
    @Bean
    public RedisCacheManager redisCacheManager(RedisManager redisManager) {
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager);
        return redisCacheManager;
    }

    @Bean
    public CookieRememberMeManager rememberMeManager() {
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        return cookieRememberMeManager;
    }

    /**
     * 安全管理器
     *
     * @param shiroRealm
     * @param sessionManager
     * @param redisCacheManager
     * @param rememberMeManager
     * @return
     */
    @Bean
    public DefaultWebSecurityManager securityManager(ShiroRealm shiroRealm,
                                                     SessionManager sessionManager,
                                                     RedisCacheManager redisCacheManager,
                                                     CookieRememberMeManager rememberMeManager) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(shiroRealm);
        securityManager.setSessionManager(sessionManager);
        //身份验证器
        //缓存
        securityManager.setCacheManager(redisCacheManager);
        securityManager.setRememberMeManager(rememberMeManager);
        return securityManager;
    }


    /**
     * 开启注解
     *
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager
                                                                                           securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new
                AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    /**
     * shiro下的service、controller需使用注解@Transactional/@Cacheable
     *
     * @return
     */
    @Bean
    @ConditionalOnMissingBean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator app = new DefaultAdvisorAutoProxyCreator();
        app.setProxyTargetClass(true);
        return app;
    }
}

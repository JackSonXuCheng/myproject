package com.myproject.myprojectsecurity.config.oauth_config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * @author jackson
 * @version 1.0
 * @date 2019/11/2 15:14
 * @comment:
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.allowFormAuthenticationForClients();
        security.tokenKeyAccess("isAuthenticated()");
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {

        endpoints.accessTokenConverter(jwtAccessTokenConverter());
        endpoints.tokenStore(jwtTokenStore());

    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {


        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        jwtAccessTokenConverter.setSigningKey("cjs");
        return jwtAccessTokenConverter;
    }

    @Bean
    public JwtTokenStore jwtTokenStore() {

        return new JwtTokenStore(jwtAccessTokenConverter());
    }


}

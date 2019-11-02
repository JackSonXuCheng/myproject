package com.myproject.myprojectsecurity.config.security_config;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * @author jackson
 * @version 1.0
 * @date 2019/11/2 10:52
 * @comment: 实现用户角色认证
 */
@Component
public class UserDetailServiceImpl implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ArrayList<GrantedAuthority> list = new ArrayList<>();
        //BCrypt 加密 密码 123
        return new User(username, "$2a$10$XcigeMfToGQ2bqRToFtUi.sG1V.HhrJV6RBjji1yncXReSNNIPl1K", list);
    }
}

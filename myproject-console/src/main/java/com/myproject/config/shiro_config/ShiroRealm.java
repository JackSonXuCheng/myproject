package com.myproject.config.shiro_config;

import com.myproject.pojo.po.Admin;
import com.myproject.pojo.po.AdminRole;
import com.myproject.pojo.po.Role;
import com.myproject.service.AdminRoleService;
import com.myproject.service.AdminService;
import com.myproject.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author jackson
 * @version 1.0
 * @date 2019/9/4 16:17
 * @comment: 认证与授权
 */
@Slf4j
@Component
public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    private AdminService adminService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private AdminRoleService adminRoleService;

    /**
     * 授权，获取用户角色和权限
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //从凭证中获取用户名
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        //根据用户名查询用户对象
        Admin admin = new Admin();
        admin.setUsername(username);
        admin = adminService.selectOne(admin);
        if (admin != null) {
            //查询用户所拥有的角色
            AdminRole adminRole = new AdminRole();
            adminRole.setAdminId(admin.getId());
            List<AdminRole> adminRoles = adminRoleService.selectByEntity(adminRole);
            List<Long> roleIds = new ArrayList<>();
            //查询角色的所有ID
            if (!CollectionUtils.isEmpty(adminRoles)) {
                roleIds = adminRoles.stream().map(AdminRole::getRoleId).collect(Collectors.toList());
            }
            //查询角色的权限
            List<String> list = new ArrayList<>();
            if (!CollectionUtils.isEmpty(roleIds)) {
                roleIds.forEach(id -> {
                    Role role = roleService.selectByKey(id);
                    if (!CollectionUtils.isEmpty(role.getAuthorities())) {
                        list.addAll(role.getAuthorities());
                    }
                });
            }
            //赋予用户角色权限
            if (!CollectionUtils.isEmpty(list)) {
                SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
                info.addStringPermissions(list);
                return info;
            }
        }

        return null;
    }

    /**
     * 用户认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws
            AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();
        String pwd = Arrays.toString(token.getPassword());
        Admin admin = new Admin();
        admin.setUsername(username);
        admin = adminService.selectOne(admin);
        if (Objects.isNull(admin)) {
            throw new UnknownAccountException();
        }
        if (!DigestUtils.md5Hex(pwd).equals(admin.getPwd())) {
            throw new IncorrectCredentialsException();
        }
        return new SimpleAuthenticationInfo(admin.getUsername(), pwd, getName());
    }
}

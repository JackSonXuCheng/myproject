package com.myproject.console.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author jackson
 * @version 1.0
 * @date 2019/9/6 10:38
 * @comment:后台界面登陆
 */
@Controller
@RequestMapping("console")
@Slf4j
public class LoginController {

    @PostMapping("login")
    public String login(String username, String password, Model model) {
        String msg = "";
        //验证身份和登陆
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            subject.login(token);
            return "redirect:index";
        } catch (UnknownAccountException e) {
            msg = "账号不存在";
        } catch (IncorrectCredentialsException e) {
            msg = "密码错误";
        } catch (LockedAccountException e) {
            msg = "连续登录失败超过5次,已被锁定";
        } catch (DisabledAccountException e) {
            msg = "账号不可用";
        }
        return "login";
    }

    @GetMapping("/logout")
    public String logout() {
        SecurityUtils.getSubject().logout();
        return "redirect:login";
    }
}

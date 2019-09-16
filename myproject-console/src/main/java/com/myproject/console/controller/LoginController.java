package com.myproject.console.controller;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

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

    @Autowired
    private Producer captchaProducer;


    @PostMapping("login")
    public String login(String username, String password, String code, Model model, HttpServletRequest request) {
        String msg = "";
        //校验验证码
        if (!Objects.equals(code, request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY))) {
            msg = "验证码不正确";
            model.addAttribute("msg", msg);
            return "login";
        }
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
        model.addAttribute("msg", msg);
        return "login";
    }

    @GetMapping("/logout")
    public String logout() {
        SecurityUtils.getSubject().logout();
        return "redirect:login";
    }

    @GetMapping("/img/getKaptchaImage")
    public void getKaptchaImage(HttpServletRequest request, HttpServletResponse response) {
        ServletOutputStream out = null;
        try {
            response.setDateHeader("Expires", 0);
            // Set standard HTTP/1.1 no-cache headers.
            response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
            // Set IE extended HTTP/1.1 no-cache headers (use addHeader).
            response.addHeader("Cache-Control", "post-check=0, pre-check=0");
            // Set standard HTTP/1.0 no-cache header.
            response.setHeader("Pragma", "no-cache");
            // return a jpeg
            response.setContentType("image/jpeg");
            // create the text for the image
            String capText = captchaProducer.createText();
            //将验证码存到session
            HttpSession session = request.getSession();
            session.setAttribute(Constants.KAPTCHA_SESSION_KEY, capText);
            log.info(capText);
            // create the image with the text
            BufferedImage bi = captchaProducer.createImage(capText);
            out = response.getOutputStream();
            // write the data out
            ImageIO.write(bi, "jpg", out);
            out.flush();
        } catch (Exception e) {
            log.warn("获取验证码失败");
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}

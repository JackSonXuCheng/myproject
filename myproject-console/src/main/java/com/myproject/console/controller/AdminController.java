package com.myproject.console.controller;

import com.myproject.common.Base.Result;
import com.myproject.pojo.po.Admin;
import com.myproject.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


/**
 * @author jackson
 * @version 1.0
 * @date 2019/8/29 15:09
 */
@Controller
@RequestMapping("admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @ResponseBody
    @GetMapping("hello")
    public Result hello() {
        List<Admin> list = adminService.exec("select * from m_admin");
        List<Admin> admins = adminService.selectAll();

        return Result.success(list);
    }

}

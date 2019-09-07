package com.myproject.console.controller;

import com.github.pagehelper.PageInfo;
import com.myproject.common.Base.Result;
import com.myproject.pojo.base.PageVO;
import com.myproject.pojo.po.Admin;
import com.myproject.service.AdminService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


/**
 * @author jackson
 * @version 1.0
 * @date 2019/8/29 15:09
 */
@Controller
@RequestMapping("/console/admin")
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

    /**
     * 列表页展示
     *
     * @param
     * @param model
     * @return
     */
    @GetMapping("list")
    public String list(Admin admin, PageVO pageVO, Model model) {
        PageInfo pageResult = adminService.queryByPage(pageVO, admin);
        model.addAttribute("searchCdt", admin);
        model.addAttribute("pageResult", pageResult);
        return "admin/list";
    }

    /**
     * 编辑页展示
     *
     * @param id
     * @param model
     * @return
     */
    @GetMapping("edit")
    public String edit(Long id, Model model) {
        model.addAttribute("admin", adminService.selectByKey(id));
        return "admin/edit";
    }


    /**
     * 提交保存
     *
     * @param
     * @return
     */
    @PostMapping("submit")
    public String submit(Admin admin, RedirectAttributes attr) {
        //判断Admin是否存在
        if (admin.getId() != null) {
            //编辑
            if (StringUtils.isNotBlank(admin.getPassword())) {
                admin.setPassword(DigestUtils.md5Hex(admin.getPassword()));
            }
        } else {
            //新增
            admin.setPassword(DigestUtils.md5Hex(admin.getPassword()));
        }
        Admin a;
        if (StringUtils.isNotBlank(admin.getPassword())) {
            a = adminService.saveOrUpdate(admin);
        } else {
            a = adminService.saveOrUpdateIgnore(admin, "password");
        }
        attr.addFlashAttribute("msg", "操作成功");
        return "redirect:list";
    }

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    @PostMapping("delete")
    @ResponseBody
    public Result<String> delete(Long[] ids) {
        adminService.delete(ids);
        return Result.success(null, "操作成功");
    }
}

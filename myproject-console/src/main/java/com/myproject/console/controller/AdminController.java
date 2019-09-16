package com.myproject.console.controller;

import com.github.pagehelper.PageInfo;
import com.myproject.common.Base.Result;
import com.myproject.common.MyStringUtil;
import com.myproject.pojo.base.PageVO;
import com.myproject.pojo.po.Admin;
import com.myproject.service.AdminService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


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
     * @param
     * @param model
     * @return
     */
    @GetMapping("list")
    public String list(Admin admin, PageVO pageVO, Model model) {
        Map acMap = MyStringUtil.getAcMap(admin);
        PageInfo pageResult = adminService.queryByPageByLike(pageVO, admin, acMap);
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
    public String edit(Long id, Model model, @RequestParam(name = "pageNum", defaultValue = "1") Integer pageNum,
                       @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        model.addAttribute("pageNum", pageNum);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("admin", adminService.selectByKey(id));
        return "admin/edit";
    }

    /**
     * 添加页展示
     */
    @GetMapping("add")
    public String add(Model model, @RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue =
            "10") Integer pageSize) {
        model.addAttribute("pageNum", pageNum);
        model.addAttribute("pageSize", pageSize);
        return "admin/add";
    }

    /**
     * 提交保存
     * @param
     * @return
     */
    @PostMapping("submit")
    public String submit(Admin admin, RedirectAttributes attr, @RequestParam(name = "pageNum", defaultValue = "1")
            Integer pageNum, @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        //判断Admin是否存在
        String url = "redirect:list";
        if (admin.getId() != null) {
            //编辑
            if (StringUtils.isNotBlank(admin.getPassword())) {
                admin.setPassword(DigestUtils.md5Hex(admin.getPassword()));
            }
            url = "redirect:list?pageNum=" + pageNum + "&pageSize=" + pageSize;
        } else {
            //新增
            admin.setPassword(DigestUtils.md5Hex(admin.getPassword()));
            url = "redirect:list?pageSize=" + pageSize;

        }
        Admin a;
        if (StringUtils.isNotBlank(admin.getPassword())) {
            a = adminService.saveOrUpdate(admin);
        } else {
            a = adminService.saveOrUpdateIgnore(admin, "password");
        }
        attr.addFlashAttribute("msg", "操作成功");
        return url;
    }

    /**
     * 删除
     * @param ids
     * @return
     */
    @PostMapping("delete")
    @ResponseBody
    public Result<String> delete(Long[] ids) {
        if (CollectionUtils.isEmpty(Arrays.asList(ids))) {
            return Result.exception(null, "请选择删除对象");
        }
        adminService.delete(ids);
        return Result.success(null, "操作成功");
    }
}

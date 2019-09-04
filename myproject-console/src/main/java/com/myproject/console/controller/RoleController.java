/*
 *
 *  RoleController
 *
 */
package com.myproject.console.controller;

import com.github.pagehelper.PageInfo;
import com.myproject.common.Base.Result;
import com.myproject.pojo.base.PageVO;
import com.myproject.pojo.po.Role;
import com.myproject.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Controller - 角色
 */
@Controller
@RequestMapping("/console/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    /**
     * 列表页展示
     *
     * @param
     * @param model
     * @return
     */
    @GetMapping("list")
    public String list(Role role, PageVO pageVO, Model model) {
        PageInfo pageResult = roleService.queryByPage(pageVO, role);
        model.addAttribute("searchCdt", role);
        model.addAttribute("pageResult", pageResult);
        return "role/list";
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
        model.addAttribute("role", roleService.selectByKey(id));
        return "role/edit";
    }


    /**
     * 提交保存
     *
     * @param
     * @return
     */
    @PostMapping("submit")
    public String submit(Role role, RedirectAttributes attr) {
        roleService.saveOrUpdate(role);
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
        roleService.delete(ids);
        return Result.success(null, "操作成功");
    }
}

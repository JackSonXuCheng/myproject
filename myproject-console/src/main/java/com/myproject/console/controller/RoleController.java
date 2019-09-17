/*
 *
 *  RoleController
 *
 */
package com.myproject.console.controller;

import com.github.pagehelper.PageInfo;
import com.myproject.common.Base.Result;
import com.myproject.common.MyStringUtil;
import com.myproject.pojo.base.PageVO;
import com.myproject.pojo.po.Role;
import com.myproject.service.AdminRoleService;
import com.myproject.service.RoleService;
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
 * Controller - 角色
 */
@Controller
@RequestMapping("/console/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private AdminRoleService adminRoleService;

    /**
     * 列表页展示
     * @param
     * @param model
     * @return
     */
    @GetMapping("list")
    public String list(Role role, PageVO pageVO, Model model) {
        Map acMap = MyStringUtil.getAcMap(role);
        PageInfo pageResult = roleService.queryByPageByLike(pageVO, role, acMap);
        model.addAttribute("searchCdt", role);
        model.addAttribute("pageResult", pageResult);
        return "role/list";
    }

    /**
     * 编辑页展示
     * @param id
     * @param model
     * @return
     */
    @GetMapping("edit")
    public String edit(Long id, Model model, @RequestParam(name = "pageNum", defaultValue = "1") Integer pageNum,
                       @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        model.addAttribute("role", roleService.selectByKey(id));
        model.addAttribute("pageNum", pageNum);
        model.addAttribute("pageSize", pageSize);
        return "role/edit";
    }


    /**
     * 添加页展示
     */
    @GetMapping("add")
    public String add(Model model, @RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue =
            "10") Integer pageSize) {
        model.addAttribute("pageNum", pageNum);
        model.addAttribute("pageSize", pageSize);
        return "role/add";
    }


    /**
     * 提交保存
     * @param
     * @return
     */
    @PostMapping("submit{act}")
    public String submit(@PathVariable("act") String act, Role role, RedirectAttributes attr, @RequestParam(name =
            "pageNum", defaultValue = "1")
            Integer pageNum, @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        String url;
        if ("_edit".equals(act)) {
            url = "redirect:list?pageNum=" + pageNum + "&pageSize=" + pageSize;

        } else {
            url = "redirect:list?pageSize=" + pageSize;
        }
        roleService.saveOrUpdate(role);
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
        List<Long> roleIds = Arrays.asList(ids);
        if (CollectionUtils.isEmpty(roleIds)) {
            return Result.exception(null, "请选择删除对象");
        }
        roleService.delete(ids);
        adminRoleService.batchDeleteByRoleId(roleIds);
        return Result.success(null, "操作成功");
    }
}

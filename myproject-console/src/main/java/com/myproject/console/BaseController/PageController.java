package com.myproject.console.BaseController;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 访问页面控制
 */
@Controller
@RequestMapping("console")
public class PageController {
    @Value("${spring.profiles.active}")
    private String env;

    /**
     * 去展示页面
     *
     * @param module 模块名
     * @param page   页面名
     * @return
     */
    @GetMapping("{module}/{page}")
    public String page(@PathVariable String module, @PathVariable String page) {
        return module + "/" + page;
    }

    @GetMapping("{page}")
    public String index(@PathVariable String page, Model model) {
        model.addAttribute("env", env);
        return page;
    }
}

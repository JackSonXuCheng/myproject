package com.myproject.console.BaseController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author jackson
 * @version 1.0
 * @date 2019/8/28 17:09
 */
@Controller
public class BaseController<T> {

    @GetMapping
    public Object hello() {
        return "hello";
    }


}

package com.myproject.console.BaseController;

import com.myproject.common.Base.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author jackson
 * @version 1.0
 * @date 2019/8/28 17:09
 */
@Controller
public class BaseController {

    @GetMapping("hello")
    @ResponseBody
    public Result hello() {
        return Result.success(true, "成功");
    }


}

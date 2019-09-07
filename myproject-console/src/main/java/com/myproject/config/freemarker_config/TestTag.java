package com.myproject.config.freemarker_config;

import freemarker.template.TemplateModelException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author jackson
 * @version 1.0
 * @date 2019/9/7 17:02
 * @comment: 测试标签
 */
@Component
public class TestTag extends BaseTag {
    @Override
    public Object exec(List arguments) throws TemplateModelException {
        return exec(arguments, id -> arguments.stream().map(String::valueOf).collect(Collectors.joining()),
                FreemarkerMethodType.findById);
    }
}

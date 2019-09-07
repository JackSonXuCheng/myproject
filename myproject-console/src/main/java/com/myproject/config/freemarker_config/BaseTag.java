package com.myproject.config.freemarker_config;

import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author jackson
 * @version 1.0
 * @date 2019/9/7 16:56
 * @comment: 所有标签都继承此类
 */
public abstract class BaseTag implements TemplateMethodModelEx {

    public Object exec(List arguments, FindByIdMethod baseMethod, FreemarkerMethodType freemarkerMethodType) throws
            TemplateModelException {
        if (CollectionUtils.isEmpty(arguments)) {
            return null;
        }
        switch (freemarkerMethodType) {
            case findById:
                return baseMethod.findById(Long.valueOf(String.valueOf(arguments.get(0))));
            case findByList:
                break;
        }
        return null;
    }


    enum FreemarkerMethodType {
        findById,
        findByList;

    }

    public interface FindByIdMethod {
        Object findById(Long id);

    }
}

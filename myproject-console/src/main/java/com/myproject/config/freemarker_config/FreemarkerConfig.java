package com.myproject.config.freemarker_config;

import com.jagregory.shiro.freemarker.ShiroTags;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * @author jackson
 * @version 1.0
 * @date 2019/9/7 17:03
 * @comment: freemarker自定义标签
 */
@Configuration
public class FreemarkerConfig {

    @Autowired
    private freemarker.template.Configuration configuration;

    @Autowired
    private TestTag testTag;

    @PostConstruct
    public void setSharedVariable() {
        configuration.setSharedVariable("testTag", testTag);
        configuration.setSharedVariable("shiro", new ShiroTags());
    }
}

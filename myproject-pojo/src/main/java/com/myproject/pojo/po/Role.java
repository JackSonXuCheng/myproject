package com.myproject.pojo.po;

import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.Table;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;
import com.myproject.pojo.base.BasePojo;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author jackson
 * @version 1.0
 * @date 2019/9/3 9:31
 * @content 角色表
 */
@Table(name = "m_role")
@javax.persistence.Table(name = "m_role")
@Data
public class Role extends BasePojo implements Serializable {

    /**
     * 角色名称
     */
    @Column(name = "username", type = MySqlTypeConstant.VARCHAR)
    private String name;

    /**
     * 角色描述
     */
    @Column(name = "description", type = MySqlTypeConstant.VARCHAR, length = 64)
    private String description;

    /**
     * 权限
     */
    @Column(name = "authorities", type = MySqlTypeConstant.TEXT)
    private List<String> authorities;

}

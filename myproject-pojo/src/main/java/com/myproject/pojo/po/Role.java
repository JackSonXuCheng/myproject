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
    @Column(name = "role_name", type = MySqlTypeConstant.VARCHAR)
    private String roleName;

    /**
     * 角色描述
     */
    @Column(name = "description", type = MySqlTypeConstant.VARCHAR, length = 64)
    private String description;

    /**
     * 权限
     */
    @Column(name = "authorities", type = MySqlTypeConstant.TEXT)
    /*必须要配合这个注解才能添加成功*/
    @javax.persistence.Column(name = "authorities")
    private List<String> authorities;

}

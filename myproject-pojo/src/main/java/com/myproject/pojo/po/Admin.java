package com.myproject.pojo.po;

import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.Table;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;
import com.myproject.pojo.base.BasePojo;
import lombok.Data;

import java.io.Serializable;

/**
 * @author jackson
 * @version 1.0
 * @date 2019/8/29 11:33
 * @content 用户表
 */
@Table(name = "m_admin")
@javax.persistence.Table(name = "m_admin")
@Data
public class Admin extends BasePojo implements Serializable {

    /**
     * 用户名
     */
    @Column(name = "name", type = MySqlTypeConstant.VARCHAR)
    private String name;

    /**
     * 密码
     */
    @Column(name = "pwd", type = MySqlTypeConstant.VARCHAR)
    private String pwd;

}

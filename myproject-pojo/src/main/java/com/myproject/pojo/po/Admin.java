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
    @Column(name = "username", type = MySqlTypeConstant.VARCHAR)
    private String username;

    /**
     * 密码
     */
    @Column(name = "password", type = MySqlTypeConstant.VARCHAR)
    private String password;

    /**
     * 描述
     */
    @Column(name = "description", type = MySqlTypeConstant.VARCHAR)
    private String description;

    /**
     * 登陆IP
     */
    @Column(name = "ip", type = MySqlTypeConstant.VARCHAR)
    private String ip;

    /**
     * 登录失败次数
     */
    @Column(name = "failed_login_cnt", type = MySqlTypeConstant.INT, length = 11, defaultValue = "0")
    private Integer failedLoginCnt;

    /**
     * 是否启动
     */
    @Column(name = "is_builtin", type = MySqlTypeConstant.BIT, length = 1, defaultValue = "1")
    private Boolean isBuiltin;

    /**
     * 经度
     */
    @Column(name = "longitude", type = MySqlTypeConstant.DECIMAL, length = 18, decimalLength = 6)
    private Double longitude;
    /**
     * 维度
     */
    @Column(name = "latitude", type = MySqlTypeConstant.DECIMAL, length = 18, decimalLength = 6)
    private Double latitude;

    /**
     * 地址
     */
    @Column(name = "address", type = MySqlTypeConstant.VARCHAR)
    private String address;
}

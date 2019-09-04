package com.myproject.pojo.po;

import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.Table;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;
import com.myproject.pojo.base.BasePojo;
import lombok.Data;

/**
 * @author jackson
 * @version 1.0
 * @date 2019/9/4 16:45
 * @comment:用户与角色中间表
 */
@Table(name = "m_admin_role")
@javax.persistence.Table(name = "m_admin_role")
@Data
public class AdminRole extends BasePojo {
    /**
     * 管理员主键
     */
    @Column(name = "admin_id", type = MySqlTypeConstant.BIGINT)
    private Long adminId;
    /**
     * 角色主键
     */
    @Column(name = "role_id", type = MySqlTypeConstant.BIGINT)
    private Long roleId;
}

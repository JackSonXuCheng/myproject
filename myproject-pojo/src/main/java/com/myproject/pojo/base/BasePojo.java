package com.myproject.pojo.base;

import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * @author jackson
 * @version 1.0
 * @date 2019/8/29 11:32
 */
@Data
public abstract class BasePojo implements Serializable {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", type = MySqlTypeConstant.BIGINT, isKey = true, isAutoIncrement = true)
    private Long id;
    /**
     * 是否被删除，0=否/1=是
     */
    @Column(name = "deleted", type = MySqlTypeConstant.INT, length = 1, isNull = false, defaultValue = "0")
    private Integer deleted = 0;
    /**
     * 创建日期
     */
    @Column(name = "create_date", type = MySqlTypeConstant.TIMESTAMP, isNull = false, defaultValue =
            "CURRENT_TIMESTAMP")
    private Date createDate;
    /**
     * 更新日期
     */
    @Column(name = "update_date", type = MySqlTypeConstant.TIMESTAMP, isNull = false, defaultValue =
            "CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Date updateDate;
}
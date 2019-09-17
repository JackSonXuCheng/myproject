/*
 *
 *  AdminRoleMapper
 *
 */
package com.myproject.mapper;

import com.myproject.pojo.po.AdminRole;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * mapper - 用户角色
 */
@Mapper
public interface AdminRoleMapper extends tk.mybatis.mapper.common.Mapper<AdminRole> {
    /**
     * 批量添加
     *
     * @param p
     */
    @Insert({"<script>",
            "insert into m_admin_role (admin_id,role_id) values ",
            "<foreach collection ='roleIds' item='item' index= 'index' separator =','>",
            "(#{adminId,jdbcType=BIGINT}, #{item,jdbcType=BIGINT})",
            "</foreach>", "</script>"
    })
    void insertList(Map<String, Object> p);

    /**
     * 批量删除
     *
     * @param adminIds
     */
    @Delete({"<script>",
            "delete from m_admin_role  where admin_id in",
            "<foreach collection='list' open='(' close=')' separator=',' item='adminId'>",
            "#{adminId,jdbcType=BIGINT}",
            "</foreach>",
            "</script>"
    })
    void batchDeleteByAdminId(List<Long> adminIds);


    /**
     * 根据roleId 批量删除
     *
     * @param roleIds
     */
    @Delete({"<script>",
            "delete from m_admin_role  where role_id in",
            "<foreach collection='list' open='(' close=')' separator=',' item='adminId'>",
            "#{adminId,jdbcType=BIGINT}",
            "</foreach>",
            "</script>"
    })
    void batchDeleteByRoleId(List<Long> roleIds);
}

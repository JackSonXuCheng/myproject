/*
 *
 * AdminRoleService
 *
 */
package com.myproject.service;

import com.myproject.base.BaseService;
import com.myproject.pojo.po.Admin;
import com.myproject.pojo.po.AdminRole;

import java.util.List;

/**
 * Service - 用户角色
 */
public interface AdminRoleService extends BaseService<AdminRole> {

    /**
     * 添加或删除
     *
     * @param admin
     * @param roleIds
     */
    void saveOrUpdateAdminRole(Admin admin, Long[] roleIds);

    /**
     * 根据adminId批量删除
     *
     * @param adminIds
     */
    void batchDeleteByAdminId(List<Long> adminIds);

    /**
     * 根据roleId 批量删除
     */
    void batchDeleteByRoleId(List<Long> roleIds);
}

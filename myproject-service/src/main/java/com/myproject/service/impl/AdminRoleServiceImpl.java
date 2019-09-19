/*
 *
 *  AdminRoleServiceImpl
 *
 */
package com.myproject.service.impl;


import com.myproject.base.impl.BaseServiceImpl;
import com.myproject.mapper.AdminRoleMapper;
import com.myproject.pojo.po.Admin;
import com.myproject.pojo.po.AdminRole;
import com.myproject.service.AdminRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Service - 用户角色
 */
@Service
public class AdminRoleServiceImpl extends BaseServiceImpl<AdminRole> implements AdminRoleService {


    @Autowired
    private AdminRoleMapper adminRoleMapper;

    @Override
    @Transactional
    public void saveOrUpdateAdminRole(Admin admin, Long[] roleIds) {
        if (!CollectionUtils.isEmpty(Arrays.asList(roleIds))) {
            //先删除后添加
            AdminRole adminRole = new AdminRole();
            adminRole.setAdminId(admin.getId());
            this.deleteEntity(adminRole);
            HashMap<String, Object> map = new HashMap<>();
            map.put("adminId", admin.getId());
            map.put("roleIds", roleIds);
            adminRoleMapper.insertList(map);
        }
    }

    @Override
    public void batchDeleteByAdminId(List<Long> adminIds) {
        adminRoleMapper.batchDeleteByAdminId(adminIds);
    }

    @Override
    public void batchDeleteByRoleId(List<Long> roleIds) {
        adminRoleMapper.batchDeleteByRoleId(roleIds);
    }
}

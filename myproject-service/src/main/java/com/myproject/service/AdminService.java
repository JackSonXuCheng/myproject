package com.myproject.service;

import com.myproject.base.BaseService;
import com.myproject.pojo.po.Admin;

import java.util.List;

/**
 * @author jackson
 * @version 1.0
 * @date 2019/8/29 15:05
 */
public interface AdminService extends BaseService<Admin> {
    List<Admin> exec(String sql);
}

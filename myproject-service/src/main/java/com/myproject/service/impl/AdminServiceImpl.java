package com.myproject.service.impl;

import com.myproject.base.impl.BaseServiceImpl;
import com.myproject.mapper.AdminMapper;
import com.myproject.pojo.po.Admin;
import com.myproject.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author jackson
 * @version 1.0
 * @date 2019/8/29 15:07
 */
@Service
public class AdminServiceImpl extends BaseServiceImpl<Admin> implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public List<Admin> exec(String sql) {
        return adminMapper.exec(sql);
    }
}

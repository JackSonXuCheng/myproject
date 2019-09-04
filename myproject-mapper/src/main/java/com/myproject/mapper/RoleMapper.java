/*
 *
 *  RoleMapper
 *
 */
package com.myproject.mapper;

import com.myproject.pojo.po.Role;
import org.apache.ibatis.annotations.Mapper;

/**
 * mapper - 角色
 */
@Mapper
public interface RoleMapper extends tk.mybatis.mapper.common.Mapper<Role> {

}

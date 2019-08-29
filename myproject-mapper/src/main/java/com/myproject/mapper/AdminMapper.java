package com.myproject.mapper;

import com.myproject.pojo.po.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;


/**
 * @author jackson
 * @version 1.0
 * @date 2019/8/29 11:57
 */
@Mapper
public interface AdminMapper extends tk.mybatis.mapper.common.Mapper<Admin> {
    @Select("${sql}")
    List<Admin> exec(@Param("sql") String sql);
}

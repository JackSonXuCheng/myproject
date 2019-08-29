package com.myproject.mapper.base;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author jackson
 * @version 1.0
 * @date 2019/8/29 11:26
 */
@Mapper
public interface BaseMapper<T> {
    @Select("${sql}")
    List<T> exec(@Param("sql") String sql);
}

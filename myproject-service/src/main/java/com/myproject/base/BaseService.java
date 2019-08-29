package com.myproject.base;

import com.github.pagehelper.PageInfo;
import com.myproject.pojo.base.PageVO;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author jackson
 * @version 1.0
 * @date 2019/8/27 16:41
 */
public interface BaseService<T> {
    /**
     * 根据主键字段进行查询，方法参数必须包含完整的主键属性，查询条件使用等号
     *
     * @param key
     * @return
     */
    T selectByKey(Object key);

    /**
     * 保存一个实体，null的属性也会保存，不会使用数据库默认值
     *
     * @param entity
     * @return
     */
    int save(T entity);

    /**
     * 根据主键字段进行删除，方法参数必须包含完整的主键属性
     *
     * @param key
     * @return
     */
    int delete(Object key);

    /**
     * 根据主键字段进行删除，方法参数必须包含完整的主键属性
     *
     * @param keys
     * @return
     */
    int delete(Object... keys);

    /**
     * 根据实体属性作为条件进行删除，查询条件使用等号
     *
     * @param entity
     * @return
     */
    int deleteEntity(T entity);

    /**
     * 根据主键更新实体全部字段，null值会被更新
     *
     * @param entity
     * @return
     */
    int updateByKey(T entity);

    /**
     * 根据主键更新属性不为null的值
     *
     * @param entity
     * @return
     */
    int updateNotNull(T entity);

    /**
     * 根据Example条件进行查询
     *
     * @param example
     * @return
     */
    List<T> selectByExample(Example example);

    /**
     * 查询全部结果，select(null)方法能达到同样的效果
     *
     * @return
     */
    List<T> selectAll();

    /**
     * 根据实体中的属性值进行查询，查询条件使用等号
     *
     * @param entity
     * @return
     */
    List<T> selectByEntity(T entity);

    /**
     * 根据实体中的属性值进行查询，查询条件使用等号，降序
     *
     * @param entity
     * @param columnName 列名
     * @return
     */
    List<T> selectByEntityOrderBy(T entity, String... columnName);

    /**
     * * 根据实体中的属性值进行查询，查询条件使用等号，升序
     *
     * @param entity
     * @param columnName 列名
     * @return
     */
    List<T> selectByEntityOrderByDesc(T entity, String... columnName);

    /**
     * 保存或者更新，如果数据不存在就保存，存在就更新
     *
     * @param entity
     * @return
     */
    T saveOrUpdate(T entity);

    /**
     * 根据实体中的属性进行查询，只能有一个返回值，有多个结果是抛出异常，查询条件使用等号
     *
     * @param entity
     * @return
     */
    T selectOne(T entity);

    /**
     * 分页查询
     *
     * @param pageVO
     * @return
     */
    PageInfo queryByPage(PageVO pageVO, T entity);


    /**
     * 忽略某些属性更新
     *
     * @param entity
     * @param properties
     * @return
     */
    T saveOrUpdateIgnore(T entity, String... properties);

    /**
     * 查找某个字段为NUll的实体
     *
     * @param entity
     * @param properties
     * @return
     */
    List<T> selectColumnNull(T entity, String... properties);

    /**
     * 模糊查询
     *
     * @param entity
     * @return
     */
    List<T> selectByEntityByLike(T entity, Map<String, Object> properties);


    /**
     * 模糊分页查询
     *
     * @param pageVO
     * @param entity
     * @return
     */
    PageInfo queryByPageByLike(PageVO pageVO, T entity, Map<String, Object> properties);


    /**
     * 模糊时间范围查询
     *
     * @param entity
     * @return
     */
    List<T> selectByEntityByLike(T entity, Map<String, Object> properties, Date begin, Date end);


    /**
     * 模糊分页时间范围查询
     *
     * @param pageVO
     * @param entity
     * @return
     */
    PageInfo queryByPageByLike(PageVO pageVO, T entity, Map<String, Object> properties, Date begin, Date end);


}

package com.myproject.base.impl;

import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.myproject.base.BaseService;
import com.myproject.pojo.base.PageVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author jackson
 * @version 1.0
 * @date 2019/8/27 16:52
 */
public abstract class BaseServiceImpl<T> implements BaseService<T> {

    @Autowired
    protected Mapper<T> mapper;


    @Override
    public T selectByKey(Object key) {
        return mapper.selectByPrimaryKey(key);
    }

    @Override
    @Transactional
    public int save(T entity) {
        return mapper.insert(entity);
    }

    @Override
    @Transactional
    public int delete(Object key) {
        return mapper.deleteByPrimaryKey(key);
    }

    @Override
    @Transactional
    public int delete(Object... keys) {
        int resultTotal = 0;
        for (Object key : keys) {
            resultTotal += mapper.deleteByPrimaryKey(key);
        }
        return resultTotal;
    }

    @Override
    public int deleteEntity(T entity) {
        return mapper.delete(entity);
    }

    @Override
    public int updateByKey(T entity) {
        return mapper.updateByPrimaryKey(entity);
    }

    @Override
    public int updateNotNull(T entity) {
        return mapper.updateByPrimaryKeySelective(entity);
    }

    @Override
    public List<T> selectByExample(Example example) {
        return mapper.selectByExample(example);
    }

    @Override
    public List<T> selectAll() {
        return mapper.selectAll();
    }

    @Override
    public List<T> selectByEntity(T entity) {
        return mapper.select(entity);
    }

    @Override
    public List<T> selectByEntityOrderBy(T entity, String... columnName) {
        return this.selectByEntityOrderBy(entity, true, columnName);
    }

    @Override
    public List<T> selectByEntityOrderByDesc(T entity, String... columnName) {
        return this.selectByEntityOrderBy(entity, false, columnName);
    }

    private List<T> selectByEntityOrderBy(T entity, Boolean isAsc, String... columnName) {
        if (columnName.length == 0 || columnName == null) {
            return selectByEntityOrderBy(entity, "id");
        }
        if (entity != null) {
            Example example = new Example(entity.getClass());
            String columnNames = String.join(",", columnName);
            if (isAsc) {
                example.setOrderByClause(columnNames);
            } else {
                example.setOrderByClause(columnNames + "DESC");
            }
            Example.Criteria criteria = example.createCriteria();
            //获取此类的所有字段
            Field[] fields = entity.getClass().getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                //获取此字段的值
                try {
                    Object o = field.get(entity);
                    if (o != null && o.getClass().isAnnotationPresent(Column.class)) {
                        Column column = o.getClass().getAnnotation(Column.class);
                        criteria.andEqualTo(column.name(), o);
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            return this.selectByExample(example);
        }


        return null;
    }


    @Override
    @Transactional
    public T saveOrUpdate(T entity) {
        Object id = getIdFiledValue(entity);
        T t;
        if (id != null) {
            t = selectByKey(id);
            if (t != null) {
                this.updateByKey(entity);
            } else {
                this.save(entity);
            }
        }
        {
            this.save(entity);
            t = entity;
        }
        return t;
    }

    private Object getIdFiledValue(Object obj) {
        Field[] declaredFields = obj.getClass().getSuperclass().getDeclaredFields();
        for (Field field : declaredFields) {
            if (field.getAnnotation(javax.persistence.Id.class) != null) {
                field.setAccessible(true);
                try {
                    return field.get(obj);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
        return null;
    }

    @Override
    public T selectOne(T entity) {
        return mapper.selectOne(entity);
    }

    @Override
    public PageInfo queryByPage(PageVO pageVO, T entity) {
        if (pageVO == null) {
            PageHelper.startPage(1, 10, "create_date " + " " + PageVO.OrderDirection.DESC.toString());
        } else {
            PageHelper.startPage(pageVO.getPageNum(), pageVO.getPageSize(), pageVO.getOrderColumn() + " " + pageVO
                    .getOrderDirection().toString());
        }
        setEmptyToNull(entity);
        return new PageInfo<T>(selectByEntity(entity));
    }


    @Override
    @Transactional
    public T saveOrUpdateIgnore(T entity, String... properties) {
        Object key = this.getIdFiledValue(entity);
        if (key != null) {
            T s = this.selectByKey(key);
            T t = (T) BeanUtils.instantiateClass(entity.getClass());
            BeanUtils.copyProperties(s, t);
            BeanUtils.copyProperties(entity, t, properties);
            this.updateByKey(t);
            return t;

        } else {
            this.save(entity);
        }
        return entity;
    }

    @Override
    public List<T> selectColumnNull(T entity, String... properties) {
        if (properties.length == 0 || properties == null) {
            return selectByEntity(entity);
        }
        Example example = new Example(entity.getClass());
        Example.Criteria criteria = example.createCriteria();
        for (String property : properties) {
            criteria.andIsNull(property);
        }
        List<T> list = selectByExample(example);
        return list;
    }

    @Override
    public List<T> selectByEntityByLike(T entity, Map<String, Object> properties) {
        if (properties == null || properties.size() == 0) {
            return selectByEntityOrderByDesc(entity, "id");
        }
        Example example = new Example(entity.getClass());
        Example.Criteria criteria = example.createCriteria();
        getCriteria(properties, criteria);

        example.setOrderByClause("id desc");
        try {
            return selectByExample(example);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public PageInfo queryByPageByLike(PageVO pageVO, T entity, Map<String, Object> properties) {
        if (pageVO == null) {
            PageHelper.startPage(1, 10);
        } else {
            PageHelper.startPage(pageVO.getPageNum(), pageVO.getPageSize());
        }
        setEmptyToNull(entity);
        return new PageInfo<T>(this.selectByEntityByLike(entity, properties));
    }

    /**
     * 将String类型的字段""的值置为null
     *
     * @param t
     */
    private void setEmptyToNull(T t) {
        Field[] fs = t.getClass().getDeclaredFields();
        for (Field f : fs) {
            f.setAccessible(true);
            try {
                Object o = f.get(t);
                if (o instanceof String && ((String) o).trim().isEmpty()) {
                    f.set(t, null);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public List<T> selectByEntityByLike(T entity, Map<String, Object> properties, Date begin, Date end) {
        if (properties == null || properties.size() == 0) {
            return selectByEntityOrderByDesc(entity, "id");
        }
        Example example = new Example(entity.getClass());
        Example.Criteria criteria = example.createCriteria();
        getCriteria(properties, criteria);
        criteria.andBetween("create_date", begin, end);
        example.setOrderByClause("id desc");
        try {
            return selectByExample(example);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public PageInfo queryByPageByLike(PageVO pageVO, T entity, Map<String, Object> properties, Date begin, Date end) {
        if (pageVO == null) {
            PageHelper.startPage(1, 10);
        } else {
            PageHelper.startPage(pageVO.getPageNum(), pageVO.getPageSize());
        }
        setEmptyToNull(entity);
        return new PageInfo<T>(this.selectByEntityByLike(entity, properties, begin, end));
    }


    private void getCriteria(Map<String, Object> properties, Example.Criteria criteria) {
        for (Map.Entry<String, Object> property : properties.entrySet()) {
            Object value = property.getValue();

            if (value instanceof Date) {
                criteria.andEqualTo(property.getKey(), value);
            } else {
                criteria.andLike(property.getKey(), StringUtils.isEmpty(value) ? "%" + "" + "%" : "%" + value + "%");
            }
        }

    }


}

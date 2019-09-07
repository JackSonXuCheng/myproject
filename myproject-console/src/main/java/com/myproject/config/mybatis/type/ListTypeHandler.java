package com.myproject.config.mybatis.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * pojo中List类型与数据库中Text类型映射的处理
 *
 * @author lihuazeng
 * @version Id: ListTypeHandler.java, v 0.1 2019-6-18 19:22 lihuazeng Exp $$
 */
@MappedJdbcTypes({JdbcType.CLOB})
public class ListTypeHandler extends BaseTypeHandler<List> {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, List parameter, JdbcType jdbcType) throws
            SQLException {
        String json = null;
        try {
            json = objectMapper.writeValueAsString(parameter);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        if (json != null) {
            ps.setString(i, json);
        }
    }

    @Override
    public List getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String json = rs.getString(columnName);
        return this.parseToList(json);
    }

    @Override
    public List getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String json = rs.getString(columnIndex);
        return this.parseToList(json);
    }

    @Override
    public List getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String json = cs.getString(columnIndex);
        return this.parseToList(json);
    }

    /**
     * 将JSONArray字符串解析成List
     *
     * @param json
     * @return
     */
    private List parseToList(String json) {
        objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true)
                .configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true)
                .configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        try {
            return (json == null || json.isEmpty()) ? null : objectMapper.readValue(json, List.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
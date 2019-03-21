package com.finance.handler;

import com.finance.archives.po.SexEmum;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedJdbcTypes(JdbcType.INTEGER)
@MappedTypes(value = SexEmum.class)
public class SexTypeHandler extends BaseTypeHandler<SexEmum> {
    // 设置非空性别
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, SexEmum sexEmum, JdbcType jdbcType) throws SQLException {
        preparedStatement.setInt(i,sexEmum.getId());
    }

    // 通过列名读取性别
    @Override
    public SexEmum getNullableResult(ResultSet resultSet, String col) throws SQLException {
        int sex = resultSet.getInt(col);
        if(sex != 1 && sex != 2){
            return null;
        }
        return SexEmum.getEnmuById(sex);
    }

    // 通过下标读取性别
    @Override
    public SexEmum getNullableResult(ResultSet resultSet, int i) throws SQLException {
        int sex = resultSet.getInt(i);
        if (sex != 1 && sex != 2){
            return null;
        }
        return SexEmum.getEnmuById(sex);
    }

    // 通过存储过程读取性别
    @Override
    public SexEmum getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        int sex = callableStatement.getInt(i);
        if (sex != 1 && sex != 2 ){
            return null;
        }
        return SexEmum.getEnmuById(sex);
    }
}

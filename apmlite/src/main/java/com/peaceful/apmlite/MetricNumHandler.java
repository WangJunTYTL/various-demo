package com.peaceful.apmlite;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by wangjun on 2019-02-02.
 */
public class MetricNumHandler extends BaseTypeHandler<MetricNum> {
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, MetricNum parameter, JdbcType jdbcType) throws SQLException {
        ps.setInt(i, parameter.num);
    }

    @Override
    public MetricNum getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return MetricNum.findByValue(rs.getInt(columnName));
    }

    @Override
    public MetricNum getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return MetricNum.findByValue(rs.getInt(columnIndex));
    }

    @Override
    public MetricNum getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return MetricNum.findByValue(cs.getInt(columnIndex));
    }
}

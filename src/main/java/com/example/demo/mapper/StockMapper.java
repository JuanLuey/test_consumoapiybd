package com.example.demo.mapper;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

import com.example.demo.entity.Stock;

public class StockMapper implements RowMapper<Stock> {
    @Override
    @Nullable
    public Stock mapRow(ResultSet rs, int rowNum) throws SQLException {        
        var result = new Stock();
        result.setEntityCode(rs.getString("entityCode"));
        result.setOperationType(rs.getString("operationType"));
        result.setSkuCode(rs.getString("skuCode"));
        result.setQuantity( rs.getDouble("quantity"));
        return result;
    }
}

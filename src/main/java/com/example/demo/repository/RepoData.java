package com.example.demo.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Stock;
import com.example.demo.mapper.StockMapper;

@Repository
public class RepoData {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Stock> getstock() {

        List<Stock> stocks = null;

        String query = """
                SELECT 'SPSA-' || 81 AS ENTITYCODE,
                '33989' AS SKUCODE,
                15 AS QUANTITY,
                'SUM' AS OPERATIONTYPE
                FROM DUAL
                """;
        stocks = jdbcTemplate.query(query,
                new Object[] {},
                new int[] {},
                new StockMapper());

        return stocks;
    }

}
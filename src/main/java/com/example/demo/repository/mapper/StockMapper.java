package com.example.demo.repository.mapper;

import com.example.demo.models.Stock;

import java.sql.ResultSet;
import java.sql.SQLException;

public final class StockMapper {

    public StockMapper() {
    }

    public static Stock stockMapper(final ResultSet resultSet) throws SQLException {
        Stock stock = new Stock();
        return stock;
    }
}

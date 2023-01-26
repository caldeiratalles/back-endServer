package com.example.demo.repository.mapper;

import com.example.demo.models.dto.CategoriaItemDTO;
import com.example.demo.models.dto.StockDTO;

import java.sql.ResultSet;
import java.sql.SQLException;

public final class StockMapper {

    public StockMapper() {
    }

    public static StockDTO stockMapper(final ResultSet resultSet) throws SQLException {
        StockDTO stock = new StockDTO();
        stock.setItem(resultSet.getString("item"));
        stock.setQuantidadeEstoque(resultSet.getInt("qtd_estoque"));
        stock.setCategoria(resultSet.getString("categoria"));
        stock.setImagem(resultSet.getString("imagem"));
        stock.setCategoriaDoItem(resultSet.getInt("td_categoria_id_categoria"));
        stock.setId_item(resultSet.getInt("id_item"));
        return stock;
    }
}

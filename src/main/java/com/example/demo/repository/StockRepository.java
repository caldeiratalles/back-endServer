package com.example.demo.repository;

import com.example.demo.models.Stock;
import com.example.demo.models.UserCreator;
import com.example.demo.models.dto.StockDTO;
import com.example.demo.repository.mapper.StockMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StockRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(StockRepository.class);

    private final JdbcTemplate jdbcTemplate;
    public StockRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int createDonation(Stock stock){
        MapSqlParameterSource sqlParametrosSelect = new MapSqlParameterSource();
        sqlParametrosSelect.addValue("item_item",stock.getItem());
        sqlParametrosSelect.addValue("descricao_item",stock.getDescricao());
        sqlParametrosSelect.addValue("qtd_estoque_item",stock.getQuantidadeEstoque());
        sqlParametrosSelect.addValue("imagem_item",stock.getImagem());
        sqlParametrosSelect.addValue("td_categoria_id_categoria_item",stock.getCategoriaDoItem());
        sqlParametrosSelect.addValue("login_usuario",stock.getUsername());
        sqlParametrosSelect.addValue("recebe",stock.getRecebe());
        sqlParametrosSelect.addValue("doa",stock.getDoa());
        sqlParametrosSelect.addValue("qtd_doa",stock.getQuantidadeDoa());

        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("sp_cria_item_estoque");
        try {
            return (int) simpleJdbcCall.execute(sqlParametrosSelect).get("saida");
        } catch (EmptyResultDataAccessException ex) {
            LOGGER.error("Impossivel adicionar no estoque: "+stock.getItem() + "com a descrição: "+stock.getDescricao());
            return 0;
        }
    }

    public List<StockDTO> findAllPieces(){
            return this.jdbcTemplate.query(
                    "SELECT * \n" +
                            "FROM tb_item ti\n" +
                            "INNER JOIN td_categoria tc ON ti.td_categoria_id_categoria = tc.id_categoria",
                    (rs, rowNum) -> StockMapper.stockMapper(rs));
    }

    public Stock findByPiece(Stock stock, UserCreator user){
        MapSqlParameterSource sqlParametrosSelect = new MapSqlParameterSource();
        //sqlParametrosSelect.addValue("valor",bind);
        return this.jdbcTemplate.queryForObject(
                "query",
                Stock.class);
    }

    public Integer requestDonation(Stock stock){
        MapSqlParameterSource sqlParametrosSelect = new MapSqlParameterSource();
        sqlParametrosSelect.addValue("item_item",stock.getItem());
        sqlParametrosSelect.addValue("descricao_item",stock.getDescricao());
        sqlParametrosSelect.addValue("qtd_estoque_item",stock.getQuantidadeEstoque());
        sqlParametrosSelect.addValue("imagem_item",stock.getImagem());
        sqlParametrosSelect.addValue("td_categoria_id_categoria_item",stock.getCategoriaDoItem());
        sqlParametrosSelect.addValue("login_usuario",stock.getUsername());
        sqlParametrosSelect.addValue("recebe",stock.getRecebe());
        sqlParametrosSelect.addValue("doa",stock.getDoa());
        sqlParametrosSelect.addValue("qtd_doa",stock.getQuantidadeDoa());

        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("sp_cria_item_estoque");
        try {
            return (int) simpleJdbcCall.execute(sqlParametrosSelect).get("saida");
        } catch (EmptyResultDataAccessException ex) {
            LOGGER.error("Impossivel fazer a doação no estoque: "+stock.getItem() + "com a descrição: "+stock.getDescricao());
            return 0;
        }
    }
}

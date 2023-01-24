package com.example.demo.repository;

import com.example.demo.models.Stock;
import com.example.demo.models.UserCreator;
import com.example.demo.models.dto.UserDTO;
import com.example.demo.repository.mapper.StockMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class StockRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(StockRepository.class);

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public StockRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Stock createDonation(Stock stock, UserCreator user){
        MapSqlParameterSource sqlParametrosSelect = new MapSqlParameterSource();
        //sqlParametrosSelect.addValue("valor",bind);
        try {
            return this.jdbcTemplate.queryForObject(
                    "query",
                    sqlParametrosSelect,
                    (rs, rowNum) -> StockMapper.stockMapper(rs));
        } catch (EmptyResultDataAccessException ex) {
            LOGGER.error("Impossivel criar o stock com o email: "+user.getEmail());
            return null;
        }
    }

    public List<Stock> findAllPieces(){
        MapSqlParameterSource sqlParametrosSelect = new MapSqlParameterSource();
        //sqlParametrosSelect.addValue("valor",bind);
            return this.jdbcTemplate.query(
                    "query",
                    sqlParametrosSelect,
                    (rs, rowNum) -> StockMapper.stockMapper(rs));
    }

    public Stock findByPiece(Stock stock, UserCreator user){
        MapSqlParameterSource sqlParametrosSelect = new MapSqlParameterSource();
        //sqlParametrosSelect.addValue("valor",bind);
        return this.jdbcTemplate.queryForObject(
                "query",
                sqlParametrosSelect,
                (rs, rowNum) -> StockMapper.stockMapper(rs));
    }

    public Integer requestDonation(Stock stock, UserDTO user){
        MapSqlParameterSource sqlParametrosSelect = new MapSqlParameterSource();
        //sqlParametrosSelect.addValue("valor",bind);
        try {
            this.jdbcTemplate.update(
                    "query",
                    sqlParametrosSelect);
        } catch (EmptyResultDataAccessException ex) {
            LOGGER.error("Impossivel atualizar a peca requisitada pelo user com o email: "+user.getEmail());
        }
        return null;
    }
}

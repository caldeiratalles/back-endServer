package com.example.demo.repository;

import com.example.demo.models.User;
import com.example.demo.repository.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserRepository.class);

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public UserRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Optional<User> createUser(User user){
        MapSqlParameterSource sqlParametrosSelect = new MapSqlParameterSource();
        //sqlParametrosSelect.addValue("valor",bind);
        try {
            return Optional.ofNullable(this.jdbcTemplate.queryForObject(
                    "query",
                    sqlParametrosSelect,
                    (rs, rowNum) -> UserMapper.userMapper(rs)));
        } catch (EmptyResultDataAccessException ex) {
            LOGGER.error("Impossivel criar o usuario com o email: "+user.getLogin()+" para o status de "+user.getStatus());
            return Optional.empty();
        }
    }

    public Optional<User> findUser(User user){
        MapSqlParameterSource sqlParametrosSelect = new MapSqlParameterSource();
        //sqlParametrosSelect.addValue("valor",bind);
        try {
            return Optional.ofNullable(this.jdbcTemplate.queryForObject(
                    "query",
                    sqlParametrosSelect,
                    (rs, rowNum) -> UserMapper.userMapper(rs)));
        } catch (EmptyResultDataAccessException ex) {
            LOGGER.error("Impossivel encontrar o usuario com o email: "+user.getLogin()+" para o status de "+user.getStatus());
            return Optional.empty();
        }
    }

    public int deleteUser(User user){
        MapSqlParameterSource sqlParametrosSelect = new MapSqlParameterSource();
        //sqlParametrosSelect.addValue("valor",bind);
        return this.jdbcTemplate.update(
                    "query",
                    sqlParametrosSelect);
    }
}

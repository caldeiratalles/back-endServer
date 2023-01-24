package com.example.demo.repository;

import com.example.demo.models.UserCreator;
import com.example.demo.models.dto.UserDTO;
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

    public Optional<UserCreator> login(UserDTO user){
        MapSqlParameterSource sqlParametrosSelect = new MapSqlParameterSource();
        //sqlParametrosSelect.addValue("valor",bind);
        try {
            return Optional.ofNullable(this.jdbcTemplate.queryForObject(
                    "query",
                    sqlParametrosSelect,
                    (rs, rowNum) -> UserMapper.userMapper(rs)));
        } catch (EmptyResultDataAccessException ex) {
            LOGGER.error("Impossivel logar o usuario com o email: "+user.getEmail());
            return Optional.empty();
        }
    }

    public Optional<UserCreator> findUser(UserDTO user){
        MapSqlParameterSource sqlParametrosSelect = new MapSqlParameterSource();
        //sqlParametrosSelect.addValue("valor",bind);
        try {
            return Optional.ofNullable(this.jdbcTemplate.queryForObject(
                    "query",
                    sqlParametrosSelect,
                    (rs, rowNum) -> UserMapper.userMapper(rs)));
        } catch (EmptyResultDataAccessException ex) {
            LOGGER.error("Impossivel encontrar o usuario com o email: "+user.getEmail());
            return Optional.empty();
        }
    }

    public int deleteUser(UserCreator user){
        MapSqlParameterSource sqlParametrosSelect = new MapSqlParameterSource();
        //sqlParametrosSelect.addValue("valor",bind);
        return this.jdbcTemplate.update(
                    "query",
                    sqlParametrosSelect);
    }

    public int createUser(UserCreator user) {
        MapSqlParameterSource sqlParametrosSelect = new MapSqlParameterSource();
        sqlParametrosSelect.addValue("loginUsuario",user.getUsername());
        sqlParametrosSelect.addValue("senhaUsuario",user.getSenha());
        sqlParametrosSelect.addValue("tipoUsuario",user.getTipoUsuario());
        sqlParametrosSelect.addValue("nomeUsuario",user.getNome());
        sqlParametrosSelect.addValue("cpfCnpj",user.getCpfCnpj());
        sqlParametrosSelect.addValue("sexoId",user.getSexo());
        sqlParametrosSelect.addValue("email",user.getEmail());
        sqlParametrosSelect.addValue("tipoEmail",user.getTipoEmail());
        sqlParametrosSelect.addValue("telefone",user.getTelefone());
        sqlParametrosSelect.addValue("tipoTelefone",user.getTipoTelefone());
        sqlParametrosSelect.addValue("cep",user.getCep());
        sqlParametrosSelect.addValue("numeroEndereo",user.getNumeroEndereco());
        sqlParametrosSelect.addValue("complemento",user.getComplementoEndereco());
        sqlParametrosSelect.addValue("logradouro",user.getLogradouro());
        sqlParametrosSelect.addValue("bairoo",user.getBairro());
        sqlParametrosSelect.addValue("cidade",user.getCidade());
        sqlParametrosSelect.addValue("estado",user.getEstado());
        try {
            return this.jdbcTemplate.update(
                    "call sp_cria_usuario_completo (:loginUsuario,:senhaUsuario,:tipoUsuario," +
                            ":nomeUsuario,:cpfCnpj,:sexoId,:email,:tipoEmail,:telefone,:tipoTelefone,:cep,:numeroEndereo,:complemento" +
                            " ,:logradouro,:bairoo,:cidade,:estado,@saida)",
                    sqlParametrosSelect);
        } catch (EmptyResultDataAccessException ex) {
            LOGGER.error("Impossivel logar o usuario com o email: "+user.getEmail());
            return 0;
        }
    }
}

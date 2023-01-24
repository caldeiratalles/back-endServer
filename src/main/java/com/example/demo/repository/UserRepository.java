package com.example.demo.repository;

import com.example.demo.models.UserCreator;
import com.example.demo.models.dto.UserDTO;
import com.example.demo.repository.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserRepository.class);

    private final JdbcTemplate jdbcTemplate;

    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Optional<UserCreator> login(UserDTO user){
        MapSqlParameterSource sqlParametrosSelect = new MapSqlParameterSource();
        //sqlParametrosSelect.addValue("valor",bind);
        try {
            return Optional.ofNullable(this.jdbcTemplate.queryForObject(
                    "query",UserCreator.class));
        } catch (EmptyResultDataAccessException ex) {
            LOGGER.error("Impossivel logar o usuario com o email: "+user.getUsername());
            return Optional.empty();
        }
    }

    public Optional<UserCreator> findUser(UserDTO user){
        MapSqlParameterSource sqlParametrosSelect = new MapSqlParameterSource();
        //sqlParametrosSelect.addValue("valor",bind);
        try {
            return Optional.ofNullable(this.jdbcTemplate.queryForObject(
                    "query",
                    UserCreator.class));
        } catch (EmptyResultDataAccessException ex) {
            LOGGER.error("Impossivel encontrar o usuario com o email: "+user.getUsername());
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
        sqlParametrosSelect.addValue("login_usuario",user.getUsername());
        sqlParametrosSelect.addValue("senha_usuario",user.getSenha());
        sqlParametrosSelect.addValue("td_tipo_usuario_id_tipo_usuario_usuario",user.getTipoUsuario());
        sqlParametrosSelect.addValue("nome_usuario",user.getNome());
        sqlParametrosSelect.addValue("cpf_cnpj_usuario",user.getCpfCnpj());
        sqlParametrosSelect.addValue("td_sexo_id_sexo_usuario",user.getSexo());
        sqlParametrosSelect.addValue("email_usuario",user.getEmail());
        sqlParametrosSelect.addValue("td_tipo_email_id_tipo_email_usuario",user.getTipoEmail());
        sqlParametrosSelect.addValue("telefone_usuario",user.getTelefone());
        sqlParametrosSelect.addValue("td_tipo_telefone_id_tipo_telefone_usuario",user.getTipoTelefone());
        sqlParametrosSelect.addValue("cep_endereco_usuario",user.getCep());
        sqlParametrosSelect.addValue("numero_endereco_usuario",user.getNumeroEndereco());
        sqlParametrosSelect.addValue("complemento_endereco_usuario",user.getComplementoEndereco());
        sqlParametrosSelect.addValue("logradouro_usuario",user.getLogradouro());
        sqlParametrosSelect.addValue("bairro_usuario",user.getBairro());
        sqlParametrosSelect.addValue("cidade_usuario",user.getCidade());
        sqlParametrosSelect.addValue("estado_usuario",user.getEstado());
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("sp_cria_usuario_completo");
        try {
            return (int) simpleJdbcCall.execute(sqlParametrosSelect).get("saida");
        } catch (EmptyResultDataAccessException ex) {
            LOGGER.error("Impossivel logar o usuario com o email: "+user.getEmail());
            return 0;
        }
    }
}

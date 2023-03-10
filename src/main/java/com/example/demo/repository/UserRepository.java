package com.example.demo.repository;

import com.example.demo.models.UserCreator;
import com.example.demo.models.dto.UserChangeSenha;
import com.example.demo.models.dto.UserDTO;
import com.example.demo.repository.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class UserRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserRepository.class);

    private final JdbcTemplate jdbcTemplate;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public UserRepository(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public UserDTO login(UserDTO user){
        MapSqlParameterSource sqlParametrosSelect = new MapSqlParameterSource();
        sqlParametrosSelect.addValue("user",user.getUsername());
        sqlParametrosSelect.addValue("senha",user.getSenha());
        try {
            return this.namedParameterJdbcTemplate.queryForObject(
                    "SELECT login, senha FROM tb_usuario WHERE tb_usuario.login = :user AND tb_usuario.senha = md5(:senha) AND tb_usuario.ativo = 1 ",
                    sqlParametrosSelect,
                    (rs, rowNum) -> UserMapper.userMapper(rs));
        } catch (EmptyResultDataAccessException ex) {
            LOGGER.error("Impossivel logar o usuario com o email: "+user.getUsername());
            return null;
        }
    }

    public UserDTO loginAdmin(UserDTO user){
        MapSqlParameterSource sqlParametrosSelect = new MapSqlParameterSource();
        sqlParametrosSelect.addValue("user",user.getUsername());
        sqlParametrosSelect.addValue("senha",user.getSenha());
        try {
            return this.namedParameterJdbcTemplate.queryForObject(
                    "SELECT login, senha FROM tb_usuario WHERE tb_usuario.login = :user AND tb_usuario.senha = md5(:senha) AND tb_usuario.ativo = 1 AND tb_usuario.td_tipo_usuario_id_tipo_usuario = 1",
                    sqlParametrosSelect,
                    (rs, rowNum) -> UserMapper.userMapper(rs));
        } catch (EmptyResultDataAccessException ex) {
            LOGGER.error("Impossivel logar o usuario com o email: "+user.getUsername());
            return null;
        }
    }

    @Transactional
    public int deleteUser(UserDTO user){
        MapSqlParameterSource sqlParametrosSelect = new MapSqlParameterSource();
        sqlParametrosSelect.addValue("username",user.getUsername());
        sqlParametrosSelect.addValue("senha",user.getSenha());
        return this.namedParameterJdbcTemplate.update(
                    "UPDATE tb_usuario SET ativo = 0 WHERE login = :username AND senha=md5(:senha)",
                    sqlParametrosSelect);
    }

    @Transactional
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

    public int trocarSenha(UserChangeSenha user) {
        MapSqlParameterSource sqlParametrosSelect = new MapSqlParameterSource();
        sqlParametrosSelect.addValue("senhaNova",user.getSenhaNova());
        sqlParametrosSelect.addValue("username",user.getUsername());
        sqlParametrosSelect.addValue("senha",user.getSenha());
        return this.namedParameterJdbcTemplate.update(
                "UPDATE tb_usuario SET senha = md5(:senhaNova) WHERE login = :username AND senha = md5(:senha)",
                sqlParametrosSelect);
    }
}

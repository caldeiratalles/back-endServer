package com.example.demo.repository;

import com.example.demo.models.Stock;
import com.example.demo.models.StockSimple;
import com.example.demo.models.dto.CategoriaItemDTO;
import com.example.demo.models.dto.CategoriasItemDTO;
import com.example.demo.models.dto.StockDTO;
import com.example.demo.models.dto.UserDTO;
import com.example.demo.repository.mapper.StockMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class StockRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(StockRepository.class);

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    public StockRepository(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Transactional
    public int createDonation(Stock stock){
        MapSqlParameterSource sqlParametrosSelect = new MapSqlParameterSource();
        sqlParametrosSelect.addValue("item_item",stock.getItem_item());
        sqlParametrosSelect.addValue("descricao_item",stock.getDescricao_item());
        sqlParametrosSelect.addValue("qtd_estoque_item",stock.getQtd_estoque_item());
        sqlParametrosSelect.addValue("imagem_item",stock.getImagem_item());
        sqlParametrosSelect.addValue("td_categoria_id_categoria_item",stock.getTd_categoria_id_categoria_item());
        sqlParametrosSelect.addValue("login_usuario",stock.getLogin_usuario());
        sqlParametrosSelect.addValue("recebe",stock.getRecebe());
        sqlParametrosSelect.addValue("doa",stock.getDoa());
        sqlParametrosSelect.addValue("qtd_doa",stock.getQtd_doa());

        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("sp_cria_item_estoque");
        try {
            return (int) simpleJdbcCall.execute(sqlParametrosSelect).get("saida");
        } catch (EmptyResultDataAccessException ex) {
            LOGGER.error("Impossivel adicionar no estoque: "+stock.getItem_item() + "com a descri????o: "+stock.getDescricao_item());
            return 0;
        }
    }
    @Transactional
    public Integer requestDonation(Stock stock){
        MapSqlParameterSource sqlParametrosSelect = new MapSqlParameterSource();
        sqlParametrosSelect.addValue("item_item",stock.getItem_item());
        sqlParametrosSelect.addValue("descricao_item",stock.getDescricao_item());
        sqlParametrosSelect.addValue("qtd_estoque_item",stock.getQtd_estoque_item());
        sqlParametrosSelect.addValue("imagem_item",stock.getImagem_item());
        sqlParametrosSelect.addValue("td_categoria_id_categoria_item",stock.getTd_categoria_id_categoria_item());
        sqlParametrosSelect.addValue("login_usuario",stock.getLogin_usuario());
        sqlParametrosSelect.addValue("recebe",stock.getRecebe());
        sqlParametrosSelect.addValue("doa",stock.getDoa());
        sqlParametrosSelect.addValue("qtd_doa",stock.getQtd_doa());

        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("sp_cria_item_estoque");
        try {
            return (int) simpleJdbcCall.execute(sqlParametrosSelect).get("saida");
        } catch (EmptyResultDataAccessException ex) {
            LOGGER.error("Impossivel fazer a doa????o no estoque: "+stock.getItem_item() + "com a descri????o: "+stock.getDescricao_item());
            return 0;
        }
    }


    public List<StockSimple> findAllPieces(){
        MapSqlParameterSource sqlParametrosSelect = new MapSqlParameterSource();
        return this.namedParameterJdbcTemplate.query(
                    "    select i.id_item, i.item, i.descricao, i.qtd_estoque as qtd_estoque, i.imagem, i.td_categoria_id_categoria\n" +
                            "    from tb_item i\n" +
                            "    inner join td_categoria c on i.td_categoria_id_categoria = c.id_categoria\n" +
                            "    where i.ativo = 1", sqlParametrosSelect,
                    new BeanPropertyRowMapper<>(StockSimple.class));
    }

    public StockSimple findByPiece(Integer id_item){
        MapSqlParameterSource sqlParametrosSelect = new MapSqlParameterSource();
        sqlParametrosSelect.addValue("id_item",id_item);

        return this.namedParameterJdbcTemplate.queryForObject(
                "    select  i.id_item, i.item, i.descricao, i.qtd_estoque as qtd_estoque, i.imagem, i.td_categoria_id_categoria\n" +
                        "    from tb_item i\n" +
                        "    inner join td_categoria c on i.td_categoria_id_categoria = c.id_categoria\n" +
                        "    where i.ativo = 1 and i.id_item = :id_item",
                sqlParametrosSelect,
                new BeanPropertyRowMapper<>(StockSimple.class));
    }


    public List<CategoriasItemDTO> findCategorias() {
        return this.jdbcTemplate.query(
                "SELECT * FROM td_categoria",
                new BeanPropertyRowMapper<>(CategoriasItemDTO.class));
    }

    public List<CategoriaItemDTO> findCategoriabyPiece(Integer id) {
        return this.jdbcTemplate.query(
                "SELECT *\n" +
                        "FROM tb_item ti\n" +
                        "INNER JOIN td_categoria tc ON ti.td_categoria_id_categoria = tc.id_categoria\n" +
                        "WHERE  tc.id_categoria = "+id+" AND ti.ativo = 1",
                new BeanPropertyRowMapper<>(CategoriaItemDTO.class));
    }

    public int deletePeca(Integer id) {
        MapSqlParameterSource sqlParametrosSelect = new MapSqlParameterSource();
        sqlParametrosSelect.addValue("id",id);
        return this.namedParameterJdbcTemplate.update(
                "UPDATE tb_item SET ativo = 0 WHERE id_item = :id",
                sqlParametrosSelect);
    }

    public int editarPeca(StockSimple stock) {
        MapSqlParameterSource sqlParametrosSelect = new MapSqlParameterSource();
        sqlParametrosSelect.addValue("quantidade", stock.getQtd_estoque());
        sqlParametrosSelect.addValue("nomeItem", stock.getItem());
        sqlParametrosSelect.addValue("img", stock.getImagem());
        sqlParametrosSelect.addValue("id",stock.getId_item());
        sqlParametrosSelect.addValue("descricao",stock.getDescricao());
        sqlParametrosSelect.addValue("td_categoria_id_categoria",stock.getTd_categoria_id_categoria());
        return this.namedParameterJdbcTemplate.update(
                "UPDATE tb_item SET" +
                    " qtd_estoque = :quantidade, item = :nomeItem, imagem = :img, descricao = :descricao, td_categoria_id_categoria = :td_categoria_id_categoria " +
                    "WHERE id_item=:id",
                sqlParametrosSelect);
    }
}

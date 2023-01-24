package com.example.demo.models.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class StockDTO {

    private Integer id_item; //
    private String item; // item_item
    private Integer quantidadeEstoque; // qtd_estoque_item
    private String categoria; // categoria
    private String imagem; //
    private Integer categoriaDoItem; // td_categoria_id_categoria_item

}

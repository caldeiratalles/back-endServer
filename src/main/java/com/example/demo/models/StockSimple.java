package com.example.demo.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class StockSimple {

    private Integer id_item;
    private String item;
    private String descricao;
    private Integer qtd_estoque;
    private String imagem;
    private String categoria;
}

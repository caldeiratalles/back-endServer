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

    private String id_item;
    private String item;
    private Integer descricao;
    private String qtd_estoque;
    private Integer imagem;
    private String categoria;
}

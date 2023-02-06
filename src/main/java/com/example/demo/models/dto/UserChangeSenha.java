package com.example.demo.models.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class UserChangeSenha {

    private String username; //login_usuario --> ele é unico
    private String senha; //senha_usuario
    private String senhaNova;
}

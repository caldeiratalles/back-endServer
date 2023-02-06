package com.example.demo.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class UserCreator {

    private String username; //login_usuario --> ele é unico
    private String senha; //senha_usuario
    private Integer tipoUsuario; //td_tipo_usuario_id_tipo_usuario_usuario
    private String nome; //nome_usuario
    private String cpfCnpj; //cpf_cnpj_usuario
    private Integer sexo; //td_sexo_id_sexo_usuario
    private String email; //email_usuario
    private Integer tipoEmail; // td_tipo_email_id_tipo_email_usuario
    private String telefone; // telefone_usuario
    private Integer tipoTelefone; // td_tipo_telefone_id_tipo_telefone_usuario
    private String cep; // cep_endereco_usuario
    private String numeroEndereco; // numero_endereco_usuario
    private String complementoEndereco; // complemento_endereco_usuario
    private String logradouro; // logradouro_usuario
    private String bairro; // bairro_usuario
    private String cidade; // cidade_usuario
    private String estado; // estado_usuario
}

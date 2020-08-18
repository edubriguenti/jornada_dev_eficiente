package com.jornadadev.casadocodigo.detalhescompra;

import com.jornadadev.casadocodigo.entity.Compra;
import com.jornadadev.casadocodigo.entity.Pais;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class DetalheCompraResponse {
    private final String email;
    private final String nome;
    private final String sobrenome;
    private final String documento;
    private final String endereco;
    private final String complemento;
    private final String cidade;
    private final Pais pais;
    private final String telefone;
    private final String cep;
    private final boolean existeCupom;
    private final Integer valorCupom;

    public DetalheCompraResponse(Compra compra) {
        this.email = compra.getEmail();
        this.nome = compra.getNome();
        this.sobrenome = compra.getSobrenome();
        this.documento = compra.getDocumento();
        this.endereco = compra.getEndereco();
        this.complemento = compra.getComplemento();
        this.cidade = compra.getCidade();
        this.pais = compra.getPais();
        this.telefone = compra.getTelefone();
        this.cep = compra.getCep();
        this.existeCupom = compra.existeCupom();
        this.valorCupom = compra.getValorCupom();
    }
}

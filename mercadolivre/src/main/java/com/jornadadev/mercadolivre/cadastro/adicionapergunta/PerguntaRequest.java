package com.jornadadev.mercadolivre.cadastro.adicionapergunta;

import com.jornadadev.mercadolivre.entity.Pergunta;
import com.jornadadev.mercadolivre.entity.Produto;
import com.jornadadev.mercadolivre.entity.Usuario;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;

@ToString
public class PerguntaRequest {

    @NotEmpty
    @Getter
    private String titulo;

    public Pergunta toModel(Usuario interessado, Produto produto) {
        return new Pergunta(titulo, interessado, produto );
    }
}

package com.jornadadev.mercadolivre.cadastro.adicionaopiniao;

import com.jornadadev.mercadolivre.entity.Opiniao;
import com.jornadadev.mercadolivre.entity.Produto;
import com.jornadadev.mercadolivre.entity.Usuario;
import lombok.ToString;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@ToString
public class NovaOpiniaoRequest {
    @Min(1)
    @Max(5)
    private int nota;
    @NotBlank
    private String titulo;
    @NotBlank
    @Size(max = 500)
    private String descricao;

    public NovaOpiniaoRequest(@Min(1) @Max(5) int nota, @NotBlank String titulo, @NotBlank @Size(max = 500) String descricao) {
        this.nota = nota;
        this.titulo = titulo;
        this.descricao = descricao;
    }

    public Opiniao toModel(Produto produto, Usuario consumidor) {
        return new Opiniao(nota, titulo, descricao, produto, consumidor);
    }
}

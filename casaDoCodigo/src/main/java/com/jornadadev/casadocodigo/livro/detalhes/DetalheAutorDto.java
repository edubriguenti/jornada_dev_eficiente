package com.jornadadev.casadocodigo.livro.detalhes;

import com.jornadadev.casadocodigo.entity.Autor;
import lombok.Getter;

@Getter
public class DetalheAutorDto {
    private final String nome;
    private final String descricao;

    public DetalheAutorDto(Autor autor) {
        this.nome = autor.getNome();
        this.descricao = autor.getDescricao();
    }
}

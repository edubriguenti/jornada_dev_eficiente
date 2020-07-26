package com.jornadadev.casadocodigo.livro;

import lombok.Getter;

@Getter
public class LivrosCadastradosDto {

    private Integer id;
    private String titulo;

    public LivrosCadastradosDto(Integer id, String titulo) {
        this.id = id;
        this.titulo = titulo;
    }
}

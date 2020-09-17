package com.jornadadev.mercadolivre.entity;


import lombok.Getter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@ToString
public class Pergunta implements Comparable<Pergunta> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    @Getter
    private String titulo;
    @NotNull
    @ManyToOne
    @Getter
    private Usuario interessado;
    @NotNull
    @ManyToOne
    @ToString.Exclude
    private Produto produto;
    private LocalDateTime dataCriacao;

    @Deprecated
    public Pergunta(){

    }
    public Pergunta(@NotEmpty String titulo, @NotNull Usuario interessado,
                    @NotNull Produto produto) {
        this.titulo = titulo;
        this.interessado = interessado;
        this.produto = produto;
        this.dataCriacao = LocalDateTime.now();
    }

    public Usuario getDonoDoProduto() {
        return produto.getDono();
    }

    @Override
    public int compareTo(Pergunta o) {
        return this.titulo.compareTo(o.titulo);
    }
}

package com.jornadadev.mercadolivre.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@ToString
@EqualsAndHashCode
public class Opiniao {

    @EqualsAndHashCode.Exclude
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    @Min(1) @Max(5)
    @Getter
    private int nota;
    @NotBlank
    @Getter
    private String titulo;
    @NotBlank @Size(max = 500)
    @Getter
    private String descricao;
    @NotNull @Valid
    @ManyToOne
    private Produto produto;
    @NotNull @Valid
    @ManyToOne
    private Usuario consumidor;

    public Opiniao(int nota, String titulo, String descricao, Produto produto, Usuario consumidor) {
        this.nota = nota;
        this.titulo = titulo;
        this.descricao = descricao;
        this.produto = produto;
        this.consumidor = consumidor;
    }

    @Deprecated
    public Opiniao() {

    }
}

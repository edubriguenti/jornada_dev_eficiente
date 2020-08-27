package com.jornadadev.mercadolivre.entity;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class CaracteristicaProduto {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    @NotBlank
    @EqualsAndHashCode.Include
    private String nome;
    @NotBlank
    private String descricao;
    @ManyToOne @NotNull @Valid
    @ToString.Exclude
    private Produto produto;

    public CaracteristicaProduto(String nome, String descricao, Produto produto) {

        this.nome = nome;
        this.descricao = descricao;
        this.produto = produto;
    }

    @Deprecated
    public CaracteristicaProduto() {
    }
}

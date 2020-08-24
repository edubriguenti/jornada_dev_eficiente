package com.jornadadev.mercadolivre.entity;

import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;

@Entity
@ToString
public class Categoria {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @NotEmpty
    private String nome;

    @ManyToOne
    @Setter
    private Categoria categoriaMae;

    @Deprecated
    public Categoria(){

    }

    public Categoria(String nome) {
        this.nome = nome;
    }
}

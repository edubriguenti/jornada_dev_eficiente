package com.jornadadev.casadocodigo.entity;

import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@ToString
public class Estado {

    @Id
    @GeneratedValue
    private Integer id;

    @NotBlank
    private String nome;

    @ManyToOne
    @NotNull
    private Pais pais;

    public Estado(String nome, Pais pais) {
        this.nome = nome;
        this.pais = pais;
    }

    @Deprecated
    protected Estado(){

    }
}

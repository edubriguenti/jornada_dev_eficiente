package com.jornadadev.casadocodigo.entity;

import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;

@Entity
@ToString
@Table(uniqueConstraints={@UniqueConstraint(columnNames = {"nome"})})
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @NotBlank
    private String nome;

    @Deprecated
    public Categoria(){

    }

    public Categoria(String nome) {
        this.nome = nome;
    }
}

package com.jornadadev.casadocodigo.entity;

import lombok.Getter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@ToString
@Getter
@Table(uniqueConstraints={@UniqueConstraint(columnNames = {"nome"})})
public class Pais {
    @Id
    @GeneratedValue
    private Integer id;

    @NotBlank
    private String nome;

    @OneToMany
    private List<Estado> estados;

    @Deprecated
    public Pais(){

    }

    public Pais(String nome){
        this.nome = nome;
    }
}

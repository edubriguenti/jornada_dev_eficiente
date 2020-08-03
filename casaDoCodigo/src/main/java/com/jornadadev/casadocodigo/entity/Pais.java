package com.jornadadev.casadocodigo.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@ToString
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(uniqueConstraints={@UniqueConstraint(columnNames = {"nome"})})
public class Pais {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @NotBlank
    @EqualsAndHashCode.Include
    private String nome;

    @OneToMany(mappedBy = "pais")
    @ToString.Exclude
    private List<Estado> estados = new ArrayList<>();

    @Deprecated
    public Pais(){

    }

    public Pais(String nome){
        this.nome = nome;
    }

    public boolean possuiEstado() {
        return !this.estados.isEmpty();
    }
}

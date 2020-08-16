package com.jornadadev.casadocodigo.entity;

import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Future;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

@Entity
@ToString
public class CupomDesconto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String codigo;

    @Positive
    @NotNull
    @Max(value = 100)
    private Integer percentual;

    @Future
    @NotNull
    private LocalDate validade;

    @Deprecated
    protected CupomDesconto() {

    }

    public CupomDesconto(String codigo, Integer percentual, LocalDate validade) {
        this.codigo = codigo;
        this.percentual = percentual;
        this.validade = validade;
    }

}

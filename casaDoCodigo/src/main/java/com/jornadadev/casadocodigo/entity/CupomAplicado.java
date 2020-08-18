package com.jornadadev.casadocodigo.entity;

import lombok.Getter;
import lombok.ToString;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Future;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

@Embeddable
@ToString
public class CupomAplicado {

    @ManyToOne
    private CupomDesconto cupomDesconto;
    @Positive
    @Max(100)
    @NotNull
    @Getter
    private Integer percentualDescontoMomento;
    @Future
    @NotNull
    private LocalDate validadeMomento;

    public CupomAplicado(CupomDesconto cupomDesconto) {
        this.cupomDesconto = cupomDesconto;
        this.percentualDescontoMomento = cupomDesconto.getPercentualDesconto();
        this.validadeMomento = cupomDesconto.getValidade();
    }

    @Deprecated
    protected CupomAplicado(){

    }
}

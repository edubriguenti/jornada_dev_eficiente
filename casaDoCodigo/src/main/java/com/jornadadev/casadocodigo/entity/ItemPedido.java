package com.jornadadev.casadocodigo.entity;

import lombok.ToString;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Embeddable
@ToString
public class ItemPedido {

    @ManyToOne
    private Livro livro;
    private @Positive int quantidade;
    @Positive
    private BigDecimal precoMomento;

    public ItemPedido(@NotNull Livro livro, @Positive int quantidade) {
        this.livro = livro;
        this.quantidade = quantidade;
        this.precoMomento = livro.getPreco();
    }

    public BigDecimal total() {
        return precoMomento.multiply(BigDecimal.valueOf(quantidade));
    }

    @Deprecated
    protected ItemPedido(){

    }
}

package com.jornadadev.casadocodigo.entity;

import lombok.ToString;
import org.springframework.util.Assert;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@ToString
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @OneToOne
    @ToString.Exclude
    private Compra compra;

    @ElementCollection
    private @Size(min=1) Set<ItemPedido> itens = new HashSet<>();

    public Pedido(@NotNull @Valid Compra compra, @Size(min = 1) Set<ItemPedido> itens) {
        Assert.isTrue(!itens.isEmpty(), "Todo item deve ter pelo menos um item.");
        this.compra = compra;
        this.itens.addAll(itens);
    }

    @Deprecated
    public Pedido(){

    }

    public boolean totalIgual(BigDecimal total) {
        final BigDecimal totalPedido = itens.stream().map(ItemPedido::total).reduce(BigDecimal.ZERO, (atual, proximo) -> atual.add(proximo));
        return totalPedido.doubleValue() == total.doubleValue();
    }

}

package com.jornadadev.mercadolivre.fechamentocompra;

import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@ToString
public class Transacao {

    @Id
    @GeneratedValue
    private Long id;
    @NotNull
    private StatusTransacao status;
    @NotNull
    private String idTransacaoGateway;
    @ManyToOne
    @NotNull
    private Compra compra;
    @NotNull
    private LocalDateTime instante;

    public Transacao(StatusTransacao status, @NotBlank String idTransacaoGateway, Compra compra) {
        this.status = status;
        this.idTransacaoGateway = idTransacaoGateway;
        this.compra = compra;
        this.instante = LocalDateTime.now();
    }

    @Deprecated
    public Transacao(){

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Transacao transacao = (Transacao) o;

        return idTransacaoGateway.equals(transacao.idTransacaoGateway);
    }

    @Override
    public int hashCode() {
        return idTransacaoGateway.hashCode();
    }

    public boolean concluidaComSucesso() {
        return this.status.equals(StatusTransacao.SUCESSO);
    }
}

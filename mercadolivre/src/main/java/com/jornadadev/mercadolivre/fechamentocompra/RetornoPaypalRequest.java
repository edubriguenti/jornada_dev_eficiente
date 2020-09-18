package com.jornadadev.mercadolivre.fechamentocompra;

import lombok.ToString;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@ToString
public class RetornoPaypalRequest implements RetornoGatewayPagamento {

    @NotNull
    @Min(0)
    @Max(1)
    private Integer status;
    @NotBlank
    private String idTransacao;

    public RetornoPaypalRequest(@NotBlank Integer status, @NotBlank String idTransacao) {
        this.status = status;
        this.idTransacao = idTransacao;
    }

    @Override
    public Transacao toTransacao(Compra compra) {
        return new Transacao(status == 1 ? StatusTransacao.SUCESSO : StatusTransacao.FALHA, idTransacao, compra);
    }
}

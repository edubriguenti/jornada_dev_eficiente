package com.jornadadev.mercadolivre.fechamentocompra;

public interface RetornoGatewayPagamento {
    Transacao toTransacao(Compra compra);
}

package com.jornadadev.mercadolivre.fechamentocompra;

import org.springframework.web.util.UriComponentsBuilder;

public enum GatewayPagamento {

    PAYPAL {
        @Override
        String criaUrlRetorno(Compra compra, UriComponentsBuilder uriComponentsBuilder) {
            String returnUrlPaypal = uriComponentsBuilder.path("/compras/{id}/retorno-paypal").buildAndExpand(compra.getId()).toString();
            return "paypal.com/" + compra.getId() + "?redirectUrl=" + returnUrlPaypal;
        }
    }
    ,PAGSEGURO {
        @Override
        String criaUrlRetorno(Compra compra, UriComponentsBuilder uriComponentsBuilder) {
            String returnUrlPagseguro = uriComponentsBuilder.path("/compras/{id}/retorno-pagseguro").buildAndExpand(compra.getId()).toString();
            return "pagseguro.com/" + compra.getId() + "?redirectUrl=" + returnUrlPagseguro;
        }
    };

    abstract String criaUrlRetorno(Compra compra, UriComponentsBuilder uriComponentsBuilder);

}

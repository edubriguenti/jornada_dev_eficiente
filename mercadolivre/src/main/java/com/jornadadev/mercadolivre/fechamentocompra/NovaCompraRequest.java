package com.jornadadev.mercadolivre.fechamentocompra;

import com.jornadadev.mercadolivre.config.ExistsId;
import com.jornadadev.mercadolivre.entity.Produto;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@ToString
public class NovaCompraRequest {

    @ExistsId(fieldName = "id", domainClass = Produto.class)
    @NotNull
    private Long idProduto;

    @NotNull
    @Positive
    private Integer quantidade;

    @NotNull
    private GatewayPagamento gateway;

    public NovaCompraRequest(@NotNull Long idProduto,
                             @NotNull @Positive Integer quantidade,
                             @NotNull GatewayPagamento gateway) {
        this.idProduto = idProduto;
        this.quantidade = quantidade;
        this.gateway = gateway;
    }
}

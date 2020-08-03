package com.jornadadev.casadocodigo.fechamentocompra;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@ToString
public class NovoPedidoRequest {

    @Positive
    @NotNull
    private BigDecimal total;

    @Size(min = 1)
    @Valid
    @Getter
    private List<NovoPedidoItemRequest> itens = new ArrayList<>();

}

package com.jornadadev.casadocodigo.fechamentocompra;

import com.jornadadev.casadocodigo.entity.Compra;
import com.jornadadev.casadocodigo.entity.ItemPedido;
import com.jornadadev.casadocodigo.entity.Pedido;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

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

    public Function<Compra, Pedido> toModel(EntityManager em) {
        Set<ItemPedido> itensCalculados = itens.stream().map(item -> item.toModel(em)).collect(Collectors.toSet());

        return (compra) -> {
            final Pedido pedido = new Pedido(compra, itensCalculados);
            Assert.isTrue(pedido.totalIgual(total), "O total enviado não corresponde ao total real.");
            return pedido;
        };
    }
}

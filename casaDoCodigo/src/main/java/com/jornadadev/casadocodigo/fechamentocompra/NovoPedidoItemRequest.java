package com.jornadadev.casadocodigo.fechamentocompra;

import com.jornadadev.casadocodigo.config.ExistsId;
import com.jornadadev.casadocodigo.entity.ItemPedido;
import com.jornadadev.casadocodigo.entity.Livro;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@ToString
@AllArgsConstructor
public class NovoPedidoItemRequest {

    @NotNull
    @ExistsId(domainClass = Livro.class, fieldName = "id")
    @Getter
    private Integer idLivro;

    @Positive
    private int quantidade;

    public ItemPedido toModel(EntityManager em) {
        @NotNull
        final Livro livro =  em.find(Livro.class, idLivro);
        return new ItemPedido(livro, quantidade);
    }
}

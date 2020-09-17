package com.jornadadev.mercadolivre.detalheproduto;

import com.jornadadev.mercadolivre.entity.CaracteristicaProduto;
import lombok.Getter;

@Getter
public class DetalheProdutoCaracteristica {
    private final String nome;
    private final String descricao;

    public DetalheProdutoCaracteristica(CaracteristicaProduto caracteristicaProduto) {
        this.nome = caracteristicaProduto.getNome();
        this.descricao = caracteristicaProduto.getDescricao();
    }
}

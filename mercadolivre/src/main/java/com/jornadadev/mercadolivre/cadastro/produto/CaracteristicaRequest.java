package com.jornadadev.mercadolivre.cadastro.produto;

import com.jornadadev.mercadolivre.entity.CaracteristicaProduto;
import com.jornadadev.mercadolivre.entity.Produto;
import lombok.Getter;
import lombok.ToString;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@ToString
@Getter
public class CaracteristicaRequest {

    @NotBlank
    private String nome;
    @NotBlank
    private String descricao;

    public CaracteristicaRequest(@NotBlank String nome, @NotBlank String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public CaracteristicaProduto toModel(@NotNull @Valid Produto produto) {
        return new CaracteristicaProduto(this.nome, this.descricao, produto);
    }
}

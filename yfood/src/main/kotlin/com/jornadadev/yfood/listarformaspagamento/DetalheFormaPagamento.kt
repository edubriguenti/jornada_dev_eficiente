package com.jornadadev.yfood.listarformaspagamento

import com.jornadadev.yfood.entities.FormaPagamentoEnum

class DetalheFormaPagamento(formaPagamentoEnum: FormaPagamentoEnum) {

    val id: String = formaPagamentoEnum.name
    val descricao: String = formaPagamentoEnum.descricao

}

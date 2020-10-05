package com.jornadadev.yfood.listarformaspagamento

import com.jornadadev.yfood.entities.FormasPagamentoEnum

class DetalheFormaPagamento(formasPagamentoEnum: FormasPagamentoEnum) {

    val id: String = formasPagamentoEnum.name
    val descricao: String = formasPagamentoEnum.descricao

}

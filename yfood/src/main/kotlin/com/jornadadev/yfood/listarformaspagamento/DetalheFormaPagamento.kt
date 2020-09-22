package com.jornadadev.yfood.listarformaspagamento

import com.jornadadev.yfood.entities.FormasPagamento

class DetalheFormaPagamento(formasPagamento: FormasPagamento) {

    val id: String = formasPagamento.name
    val descricao: String = formasPagamento.descricao

}

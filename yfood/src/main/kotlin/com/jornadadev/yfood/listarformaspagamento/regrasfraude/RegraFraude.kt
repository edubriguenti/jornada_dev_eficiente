package com.jornadadev.yfood.listarformaspagamento.regrasfraude

import com.jornadadev.yfood.entities.FormasPagamentoEnum
import com.jornadadev.yfood.entities.Usuario

interface RegraFraude {
    fun aceita(usuario: Usuario, formaPagamento: FormasPagamentoEnum): Boolean
}

package com.jornadadev.yfood.listarformaspagamento.regrasfraude

import com.jornadadev.yfood.entities.FormaPagamentoEnum
import com.jornadadev.yfood.entities.Usuario

interface RegraFraude {
    fun aceita(usuario: Usuario, formaPagamento: FormaPagamentoEnum): Boolean
}

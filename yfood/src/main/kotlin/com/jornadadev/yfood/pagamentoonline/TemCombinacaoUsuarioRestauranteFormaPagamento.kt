package com.jornadadev.yfood.pagamentoonline

import com.jornadadev.yfood.entities.FormaPagamentoEnum

interface TemCombinacaoUsuarioRestauranteFormaPagamento {

    fun getIdRestaurante() : Long;
    fun getIdUsuario() : Long;
    fun getFormaPagamento() : FormaPagamentoEnum;

}
package com.jornadadev.yfood.listarformaspagamento.regrasfraude

import com.jornadadev.yfood.entities.FormasPagamentoEnum
import com.jornadadev.yfood.entities.Usuario
import org.springframework.stereotype.Service

@Service
class RegraUsuarioEmailFraudulento : RegraFraude {

    val emailsFraudadores = setOf("fraudador1@gmail.com", "fraudador2@uol.com")

    override fun aceita(usuario: Usuario, formaPagamento: FormasPagamentoEnum ) : Boolean {
       with(usuario) {
           if (!formaPagamento.online){
               return true
           }
          return formaPagamento.online && !emailsFraudadores.contains(email)
       }
    }
}
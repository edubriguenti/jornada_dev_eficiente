package com.jornadadev.yfood.pagamentoonline.gateway

import com.jornadadev.yfood.entities.Pagamento
import com.jornadadev.yfood.entities.Transacao
import com.jornadadev.yfood.pagamentoonline.Resultado
import org.springframework.util.Assert
import java.math.BigDecimal

abstract class Gateway {
    fun aceita(pagamento: Pagamento): Boolean {
        Assert.isTrue(!pagamento.concluido(), "Por algum motivo um motivo já concluído está tentando ser processado de novo")
        return aceiteEspecifico(pagamento)
    }

    protected abstract fun aceiteEspecifico(
            pagamento: Pagamento?): Boolean

    fun processa(pagamento: Pagamento): Resultado<Exception, Transacao> {
        aceita(pagamento)
        return processaEspecifico(pagamento)
    }

    protected abstract fun processaEspecifico(pagamento: Pagamento): Resultado<Exception, Transacao>
    abstract override fun equals(obj: Any?): Boolean
    abstract override fun hashCode(): Int
    fun custo(pagamento: Pagamento): BigDecimal {
        aceita(pagamento)
        return custoEspecifico(pagamento)
    }

    protected abstract fun custoEspecifico(pagamento: Pagamento?): BigDecimal
}
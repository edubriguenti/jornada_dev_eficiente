package com.jornadadev.yfood.pagamentoonline

import com.jornadadev.yfood.entities.Pagamento
import com.jornadadev.yfood.pagamentooffline.ObtemValorPedido
import org.springframework.stereotype.Service
import org.springframework.transaction.support.TransactionTemplate
import org.springframework.validation.BindException
import javax.persistence.EntityManager

@Service
class IniciaPagamentoOnline(
        val em: EntityManager,
        val obtemValorPedido: ObtemValorPedido,
        val transactionTemplate: TransactionTemplate
) {

    fun executa(idPedido: Long, request: NovoPagamentoOnlineRequest): Pagamento {
        //Obtem valor do pedido
        val valorPedido = obtemValorPedido.executa(idPedido) {
            val bindException = BindException(idPedido, "idPedido")
            bindException.rejectValue(null, "", "Este id de pedido não existe")
            bindException
        }

        //Salva no pagamento
        val novoPagamentoSalvo = transactionTemplate.execute {
            val pagamento = request.toPagamento(idPedido, valorPedido, em)
            em.persist(pagamento)
            pagamento
        }

        return novoPagamentoSalvo!!
    }

}

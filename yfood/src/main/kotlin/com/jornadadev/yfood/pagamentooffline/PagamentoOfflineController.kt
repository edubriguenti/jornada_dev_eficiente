package com.jornadadev.yfood.pagamentooffline

import org.springframework.transaction.support.TransactionTemplate
import org.springframework.validation.BindException
import org.springframework.web.bind.WebDataBinder
import org.springframework.web.bind.annotation.*
import java.math.BigDecimal
import javax.persistence.EntityManager
import javax.validation.Valid

@RestController
class PagamentoOfflineController(
        val em: EntityManager,
        val transactionTemplate: TransactionTemplate,
        val novoPedidoOfflineRequestValidator: NovoPedidoOfflineRequestValidator,
        val obtemValorPedido: ObtemValorPedido
) {

    @InitBinder
    fun init(binder: WebDataBinder) {
        binder.addValidators(novoPedidoOfflineRequestValidator)
    }

    @PostMapping("/pagamento/offline/{idPedido}")
    fun paga(
            @PathVariable idPedido: Long,
            @RequestBody @Valid request: NovoPedidoOfflineRequest
    ): String {
        val valorPedido = obtemValorPedido(idPedido)
        val pagamento = salvaTransacao(request, idPedido, valorPedido)
        return pagamento!!.codigo
    }

    private fun obtemValorPedido(idPedido: Long): BigDecimal {
        return obtemValorPedido.executa(idPedido) {
            val bindException = BindException(idPedido, "idPedido")
            bindException.rejectValue(null, "", "Este id de pedido não existe")
            bindException
        }
    }

    private fun salvaTransacao(request: NovoPedidoOfflineRequest, idPedido: Long, valorPedido: BigDecimal): Pagamento? {
        return transactionTemplate.execute {
            val pagamento = request.toPagamento(idPedido, valorPedido, em)
            em.persist(pagamento)
            pagamento
        }
    }

    @GetMapping("/transacoes")
    fun buscaTransacoes(): List<Pagamento> {
        return em.createQuery(
                "select t from Pagamento as t", Pagamento::class.java).resultList.toList()
    }

}
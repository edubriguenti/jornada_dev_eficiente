package com.jornadadev.yfood.pagamentoonline

import com.jornadadev.yfood.entities.StatusTransacao
import com.jornadadev.yfood.entities.Transacao
import com.jornadadev.yfood.pagamentooffline.CombinacaoUsuarioRestauranteFormaPagamentoValidator
import com.jornadadev.yfood.pagamentooffline.ObtemValorPedido
import com.jornadadev.yfood.pagamentoonline.gateway.Gateway
import org.springframework.transaction.support.TransactionTemplate
import org.springframework.validation.BindException
import org.springframework.web.bind.WebDataBinder
import org.springframework.web.bind.annotation.*
import javax.persistence.EntityManager

@RestController
class NovoPagamentoOnlineController(
        val validator: CombinacaoUsuarioRestauranteFormaPagamentoValidator,
        val obtemValorPedido: ObtemValorPedido,
        val transactionTemplate: TransactionTemplate,
        val em: EntityManager,
        val gateways: Set<Gateway>

) {

    @InitBinder
    fun init(binder: WebDataBinder) {
        binder.addValidators(validator, FormaPagamentoOnlineValidator())
    }


    @PostMapping("/pagamento/online/{idPedido}")
    fun paga(@PathVariable("idPedido") idPedido: Long,
             @RequestBody request: NovoPagamentoOnlineRequest) {

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

        //Ordena os gateways por custo
        val gatewaysOrdenados = gateways
                .filter {
                    it.aceita(novoPagamentoSalvo!!)
                }.sortedBy { gateway ->
                    gateway.custo(novoPagamentoSalvo!!)
                }

        //Tenta executar cada um dos gateways
        for (gateway in gatewaysOrdenados) {
            val possivelNovaTransacao = gateway.processa(novoPagamentoSalvo!!)
            if (possivelNovaTransacao.temErro()) {
                val falhou = Transacao(StatusTransacao.FALHA)
                falhou.setInfoAdicional(mapOf("gateway" to gateway, "exception" to possivelNovaTransacao.stackTrace))
            } else {
                transactionTemplate.execute {
                    novoPagamentoSalvo.adicionaTransacao(possivelNovaTransacao.get()!!)
                }
                break
            }

        }

    }

}
package com.jornadadev.yfood.pagamentoonline

import com.jornadadev.yfood.pagamentooffline.CombinacaoUsuarioRestauranteFormaPagamentoValidator
import com.jornadadev.yfood.pagamentooffline.ObtemValorPedido
import org.springframework.transaction.support.TransactionTemplate
import org.springframework.validation.BindException
import org.springframework.web.bind.WebDataBinder
import org.springframework.web.bind.annotation.*
import javax.persistence.EntityManager

@RestController
class NovoPagamentoOnlineController(
        val validator: CombinacaoUsuarioRestauranteFormaPagamentoValidator,
        val transactionTemplate: TransactionTemplate,
        val em: EntityManager,
        val gatewaysService: GatewaysService,
        val iniciaPagamentoOnline: IniciaPagamentoOnline
) {

    @InitBinder
    fun init(binder: WebDataBinder) {
        binder.addValidators(validator, FormaPagamentoOnlineValidator())
    }


    @PostMapping("/pagamento/online/{idPedido}")
    fun paga(@PathVariable("idPedido") idPedido: Long,
             @RequestBody request: NovoPagamentoOnlineRequest) {

        val novoPagamentoSalvo = iniciaPagamentoOnline.executa(idPedido, request)
        val transacoes = gatewaysService.processa(novoPagamentoSalvo!!)
        transactionTemplate.execute {
            novoPagamentoSalvo.adicionaTransacao(transacoes)
        }

    }

}
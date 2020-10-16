package com.jornadadev.yfood.pagamentoonline

import com.jornadadev.yfood.entities.Pagamento
import com.jornadadev.yfood.entities.StatusTransacao
import com.jornadadev.yfood.entities.Transacao
import com.jornadadev.yfood.pagamentoonline.gateway.Gateway
import org.springframework.stereotype.Service
import org.springframework.util.Assert

@Service
class GatewaysService(val gateways: Set<Gateway>) {

    fun processa(pagamento: Pagamento) : List<Transacao>{
        Assert.isTrue(!pagamento.concluido(), "Não é possível processar pagamento concluído.")
        //Ordena os gateways por custo
        val gatewaysOrdenados = gateways
                .filter {
                    it.aceita(pagamento)
                }.sortedBy { gateway ->
                    gateway.custo(pagamento)
                }

        val transacoes = mutableListOf<Transacao>()

        //Tenta executar cada um dos gateways
        for (gateway in gatewaysOrdenados) {
            val possivelNovaTransacao = gateway.processa(pagamento)
            if (possivelNovaTransacao.temErro()) {
                val falhou = Transacao(StatusTransacao.FALHA)
                falhou.setInfoAdicional(mapOf("gateway" to gateway, "exception" to possivelNovaTransacao.stackTrace))
                transacoes.add(falhou)
            } else {
                transacoes.add(possivelNovaTransacao.get()!!)
                break
            }
        }
        return transacoes
    }
}
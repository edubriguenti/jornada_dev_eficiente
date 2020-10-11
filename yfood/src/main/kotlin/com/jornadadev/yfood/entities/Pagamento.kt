package com.jornadadev.yfood.entities

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.util.Assert
import java.math.BigDecimal
import java.util.*
import javax.persistence.*
import javax.validation.Valid
import javax.validation.constraints.NotNull
import javax.validation.constraints.Positive

@Entity
open class Pagamento(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long = 0,
        @field:NotNull
        val idPedido: Long,
        @field:NotNull
        @field:Positive
        val valor: BigDecimal,
        @field:NotNull
        @field:ManyToOne
        @field:Valid
        val usuario: Usuario,
        @field:NotNull
        @field:ManyToOne
        @field:Valid
        val restaurante: Restaurante,
        val codigo: String = UUID.randomUUID().toString(),
        @NotNull
        val formaPagamento: FormaPagamentoEnum,
        statusTransacao: StatusTransacao
) {

    private var informacoesAdicionais:String = ""
    @ElementCollection
    val transacoes = mutableSetOf<Transacao>()
    init {
        transacoes.add(Transacao(status = statusTransacao))
    }

    companion object {
        fun cartao(idPedido: Long,
                   valor: BigDecimal,
                   numeroCartao: String,
                   codigoSeguranca: Int,
                   formaPagamento: FormaPagamentoEnum,
                   usuario: Usuario,
                   restaurante: Restaurante,
                   statusTransacao: StatusTransacao) : Pagamento {
            Assert.isTrue(formaPagamento.online, "A forma de pagamento de ser online.")
            val pagamento = Pagamento(
                    idPedido = idPedido,
                    valor = valor,
                    formaPagamento = formaPagamento,
                    usuario = usuario,
                    restaurante = restaurante,
                    statusTransacao = statusTransacao
            )
            pagamento.setInformacoesAdicionais(mapOf("numero" to numeroCartao,
                    "codigoSeguranca" to codigoSeguranca))
            return pagamento
        }
    }

    private fun setInformacoesAdicionais(infoAdicionais: Map<String, Any>) {
        val mapper = ObjectMapper()
        this.informacoesAdicionais = mapper.writeValueAsString(infoAdicionais)
    }

    fun conclui() {
        Assert.state(!concluido(), "Você não pode concluir uma conta que já concluída.")
        this.transacoes.add(Transacao(StatusTransacao.CONCLUIDA))
    }

    fun concluido(): Boolean {
        return transacoes.any { it.status == StatusTransacao.CONCLUIDA }
    }
}

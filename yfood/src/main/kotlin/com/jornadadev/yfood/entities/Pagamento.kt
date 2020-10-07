package com.jornadadev.yfood.entities

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
        statusTransacao: StatusTransacao
) {
    fun conclui() {
        Assert.state(!concluido(), "Você não pode concluir uma conta que já concluída.")
        this.transacoes.add(Transacao(StatusTransacao.CONCLUIDA))
    }

    fun concluido(): Boolean {
        return transacoes.any { it.status == StatusTransacao.CONCLUIDA }
    }

    @ElementCollection
    val transacoes = mutableSetOf<Transacao>()
    init {
        transacoes.add(Transacao(status = statusTransacao))
    }
}

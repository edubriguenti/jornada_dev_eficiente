package com.jornadadev.yfood.pagamentooffline

import com.jornadadev.yfood.entities.Restaurante
import com.jornadadev.yfood.entities.StatusTransacao
import com.jornadadev.yfood.entities.Transacao
import com.jornadadev.yfood.entities.Usuario
import java.math.BigDecimal
import java.util.*
import javax.persistence.*
import javax.validation.Valid
import javax.validation.constraints.NotNull
import javax.validation.constraints.Positive

@Entity
class Pagamento(
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
    @ElementCollection
    val transacoes = mutableSetOf<Transacao>()
    init {
        transacoes.add(Transacao(status = statusTransacao))
    }
}

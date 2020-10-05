package com.jornadadev.yfood.pagamentooffline

import com.jornadadev.yfood.config.ExistsId
import com.jornadadev.yfood.entities.FormasPagamentoEnum
import com.jornadadev.yfood.entities.Restaurante
import com.jornadadev.yfood.entities.StatusTransacao
import com.jornadadev.yfood.entities.Usuario
import java.math.BigDecimal
import javax.persistence.EntityManager
import javax.validation.constraints.NotNull

data class NovoPedidoOfflineRequest(
        @field:NotNull
        val formaPagamento: FormasPagamentoEnum,
        @field:NotNull
        @field:ExistsId(fieldName = "id", domainClass = Restaurante::class)
        val idRestaurante: Long,
        @field:ExistsId(fieldName = "id", domainClass = Usuario::class)
        val idUsuario: Long

) {
    fun isOffline(): Boolean = !formaPagamento.online
    fun toPagamento(idPedido: Long, valor: BigDecimal, em: EntityManager): Pagamento {
        val usuario = em.find(Usuario::class.java, idUsuario)
        val restaurante = em.find(Restaurante::class.java, idPedido)
        return Pagamento(
                idPedido = idPedido,
                valor = valor,
                usuario = usuario,
                restaurante = restaurante,
                statusTransacao = StatusTransacao.ESPERANDO_CONFIRMACAO_PAGAMENTO
        )
    }
}
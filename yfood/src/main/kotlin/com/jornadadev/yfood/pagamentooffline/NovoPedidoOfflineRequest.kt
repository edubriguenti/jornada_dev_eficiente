package com.jornadadev.yfood.pagamentooffline

import com.jornadadev.yfood.config.ExistsId
import com.jornadadev.yfood.entities.*
import com.jornadadev.yfood.pagamentoonline.TemCombinacaoUsuarioRestauranteFormaPagamento
import java.math.BigDecimal
import javax.persistence.EntityManager
import javax.validation.constraints.NotNull

data class NovoPedidoOfflineRequest(
        @field:NotNull
        private val formaPagamento: FormaPagamentoEnum,
        @field:NotNull
        @field:ExistsId(fieldName = "id", domainClass = Restaurante::class)
        private val idRestaurante: Long,
        @field:ExistsId(fieldName = "id", domainClass = Usuario::class)
        private val idUsuario: Long

) : TemCombinacaoUsuarioRestauranteFormaPagamento {
    fun isOffline(): Boolean = !formaPagamento.online
    fun toPagamento(idPedido: Long, valor: BigDecimal, em: EntityManager): Pagamento {
        val usuario = em.find(Usuario::class.java, idUsuario)
        val restaurante = em.find(Restaurante::class.java, idPedido)
        return Pagamento(
                idPedido = idPedido,
                valor = valor,
                usuario = usuario,
                restaurante = restaurante,
                statusTransacao = StatusTransacao.ESPERANDO_CONFIRMACAO_PAGAMENTO,
                formaPagamento = formaPagamento
        )
    }

    override fun getIdRestaurante(): Long {
        return idRestaurante
    }

    override fun getIdUsuario(): Long {
        return idUsuario
    }

    override fun getFormaPagamento(): FormaPagamentoEnum {
        return formaPagamento
    }

}
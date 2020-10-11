package com.jornadadev.yfood.pagamentoonline

import com.jornadadev.yfood.config.ExistsId
import com.jornadadev.yfood.entities.*
import org.hibernate.validator.constraints.CreditCardNumber
import java.math.BigDecimal
import javax.persistence.EntityManager
import javax.validation.constraints.Max
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

class NovoPagamentoOnlineRequest(
        @NotNull
        private val formaPagamento: FormaPagamentoEnum,
        @NotNull
        @ExistsId(domainClass = Restaurante::class, fieldName = "id")
        private val idRestaurante: Long,
        @NotNull
        @ExistsId(domainClass = Usuario::class, fieldName = "id")
        private val idUsuario: Long,
        @CreditCardNumber
        @NotBlank
        private val numeroCartao: String,
        @Min(100)
        @Max(999)
        private val codigoSeguracao: Int

) : TemCombinacaoUsuarioRestauranteFormaPagamento{

    fun pagamentoOnline(): Boolean {
        return formaPagamento.online
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

    fun toPagamento(idPedido: Long, valorPedido: BigDecimal, em: EntityManager) : Pagamento {
        val comprador = em.find(Usuario::class.java, idUsuario)
        val restaurante = em.find(Restaurante::class.java, idRestaurante)
        return Pagamento.cartao(
                idPedido = idPedido,
                valor = valorPedido,
                usuario = comprador,
                restaurante = restaurante,
                statusTransacao = StatusTransacao.ESPERANDO_CONFIRMACAO_PAGAMENTO,
                formaPagamento = this.formaPagamento,
                numeroCartao = numeroCartao,
                codigoSeguranca = codigoSeguracao
        )

    }

}

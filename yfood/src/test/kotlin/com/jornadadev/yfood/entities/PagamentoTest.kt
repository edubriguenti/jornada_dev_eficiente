package com.jornadadev.yfood.entities

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.lang.IllegalStateException
import java.math.BigDecimal

class PagamentoTest {

    val pagamento = Pagamento(
            idPedido = 1L,
            valor = BigDecimal.ZERO,
            usuario = Usuario("email@email.com", setOf(FormasPagamentoEnum.DINHEIRO)),
            restaurante = Restaurante("Restaurante 1", setOf(FormasPagamentoEnum.DINHEIRO)),
            statusTransacao = StatusTransacao.ESPERANDO_CONFIRMACAO_PAGAMENTO
    )

    @Test
    fun `verifica pagamento ainda nao concluido` () {
        assertFalse(pagamento.concluido())
    }

    @Test
    fun `verifica pagamento foi concluido` () {
        pagamento.conclui()
        assertTrue(pagamento.concluido())
    }

    @Test
    fun `não deve deixar concluir pagamento mais de uma vez` () {
        pagamento.conclui()
        assertThrows(IllegalStateException::class.java) {
            pagamento.conclui()
        }
    }

}
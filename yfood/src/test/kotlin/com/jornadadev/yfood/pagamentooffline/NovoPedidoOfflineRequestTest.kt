package com.jornadadev.yfood.pagamentooffline

import com.jornadadev.yfood.entities.FormasPagamentoEnum
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class NovoPedidoOfflineRequestTest {

    @Test
    fun `deve verificar se um pedido é offline`() {
        val request = NovoPedidoOfflineRequest(FormasPagamentoEnum.DINHEIRO, 1,1)
        assertTrue(request.isOffline())
    }

    @Test
    fun `deve verificar se um pedido é não offline`() {
        val request = NovoPedidoOfflineRequest(FormasPagamentoEnum.VISA, 1,1)
        assertFalse(request.isOffline())
    }
}
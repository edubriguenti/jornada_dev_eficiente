package com.jornadadev.yfood.pagamentooffline

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.springframework.web.client.RestTemplate
import java.lang.Exception
import java.math.BigDecimal

internal class ObtemValorPedidoTest {


    @Test
    fun `se id do pedido existe deve obter valor pedido` () {

        val restTemplate = mock(RestTemplate::class.java)
        `when`(restTemplate.getForObject("http://localhost:8080/api/pedidos/{idPedido}",
                Map::class.java, 1L)).thenReturn(mapOf("valor" to "10.0"))

        val obtemValorPedido = ObtemValorPedido(restTemplate)

        val valor = obtemValorPedido.executa(1L) {
            Exception()
        }

        assertThat(valor, `is`(equalTo(BigDecimal("10.0"))))
    }

}
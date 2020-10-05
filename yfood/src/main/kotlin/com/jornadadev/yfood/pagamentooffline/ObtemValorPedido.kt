package com.jornadadev.yfood.pagamentooffline

import org.springframework.stereotype.Component
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.RestTemplate
import java.math.BigDecimal

@Component
class ObtemValorPedido(val restTemplate: RestTemplate) {

    fun executa(idPedido:Long, acaoCasoErro: () -> Exception) : BigDecimal {
        try {
            val pedido = restTemplate.getForObject("http://localhost:8080/api/pedidos/{idPedido}",
                    Map::class.java, idPedido) as Map<String, String>
            return BigDecimal(pedido["valor"] ?: "0.0")
        } catch (e: HttpClientErrorException) {
            throw acaoCasoErro.invoke()
        }
    }
}
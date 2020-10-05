package com.jornadadev.yfood.outrossistemas

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException
import java.math.BigDecimal
import java.util.concurrent.atomic.AtomicInteger
import kotlin.random.Random

@RestController
class EndpointsExternos {

    val ids = AtomicInteger()

    @GetMapping("/api/pedidos/{idPedido}")
    fun valorPedido(@PathVariable("idPedido") idPedido: Long) : Map<String, Any> {
        if (ids.getAndIncrement() % 3 == 0) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND)
        }

        val valores = arrayOf("50.0", "150.0", "60", "200")
        val valor = valores[Random.nextInt(4)]
        return mapOf("valor" to valor)
    }

}
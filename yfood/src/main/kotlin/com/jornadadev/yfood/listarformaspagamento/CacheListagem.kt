package com.jornadadev.yfood.listarformaspagamento

import com.jornadadev.yfood.entities.Restaurante
import com.jornadadev.yfood.entities.Usuario
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.support.TransactionTemplate
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.persistence.EntityManager

@Service
class CacheListagem (
        val transactionTemplate: TransactionTemplate,
        val em: EntityManager
        ){

    @Value("\${cache.usuario-seleciona-restaurante.quantidade}")
    private val nVezes : Int = 1

    @Value("\${cache.usuario-seleciona-restaurante.tempo-expiracao}")
    private val tempoExpiracao : Int = 10

    fun executa(formasPagamentos: Set<DetalheFormaPagamento>, usuario: Usuario, restaurante: Restaurante): ResponseEntity<Set<DetalheFormaPagamento>> {
        transactionTemplate.execute {
            usuario.registraSelecao(restaurante)
            em.merge(usuario)
        }

        if (usuario.selecionou(restaurante, nVezes)) {
            return ResponseEntity.ok()
                    .header("Expires", LocalDateTime.now().plusHours(tempoExpiracao.toLong()).format(DateTimeFormatter.ISO_LOCAL_DATE_TIME))
                    .body(formasPagamentos)
        }

        return ResponseEntity.ok(formasPagamentos)

    }

}

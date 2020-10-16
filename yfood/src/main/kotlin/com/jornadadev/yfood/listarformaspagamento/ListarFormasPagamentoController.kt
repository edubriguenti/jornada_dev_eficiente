package com.jornadadev.yfood.listarformaspagamento

import com.jornadadev.yfood.entities.Restaurante
import com.jornadadev.yfood.entities.Usuario
import com.jornadadev.yfood.listarformaspagamento.regrasfraude.RegraFraude
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.ResponseEntity
import org.springframework.transaction.annotation.Transactional
import org.springframework.transaction.support.TransactionTemplate
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.lang.IllegalArgumentException
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.persistence.EntityManager
import javax.validation.constraints.NotNull
import javax.validation.constraints.Positive

@RestController
@RequestMapping("/formaspagamento")
@Validated
class ListarFormasPagamentoController(
        val em: EntityManager,
        val regrasFraude: Set<RegraFraude>,
        val transactionTemplate: TransactionTemplate,
        val cacheListagem: CacheListagem
) {

    @GetMapping
    @Transactional
    fun obterFormasPagamento(
            @NotNull @Positive @RequestParam(value = "idUsuario") idUsuario: Long,
            @NotNull @Positive @RequestParam(value = "idRestaurante") idRestaurante: Long
    ) : ResponseEntity<Set<DetalheFormaPagamento>> {

        val (usuario, restaurante) = validarEntrada(idUsuario, idRestaurante)
        transactionTemplate.execute {
            usuario.registraSelecao(restaurante)
            em.merge(usuario)
        }
        val formasPagamentos = usuario.filtraFormasPagamento(restaurante, regrasFraude).map { DetalheFormaPagamento(it) }.toSet()
        return cacheListagem.executa(formasPagamentos, usuario, restaurante)
    }

    private fun validarEntrada(idUsuario: Long, idRestaurante: Long): Pair<Usuario, Restaurante> {
        val usuario = em.find(Usuario::class.java, idUsuario) ?: throw IllegalArgumentException("Usuário não encontrado")
        val restaurante = em.find(Restaurante::class.java, idRestaurante)
                ?: throw IllegalArgumentException("Restaurante não encontrado")
        return Pair(usuario, restaurante)
    }
}
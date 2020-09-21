package com.jornadadev.yfood.listarformaspagamento

import com.jornadadev.yfood.entities.FormasPagamento
import com.jornadadev.yfood.entities.Restaurante
import com.jornadadev.yfood.entities.Usuario
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.lang.IllegalArgumentException
import javax.persistence.EntityManager
import javax.validation.constraints.NotNull
import javax.validation.constraints.Positive

@RestController
@RequestMapping("/formaspagamento")
@Validated
class ListarFormasPagamentoController(val em: EntityManager) {

    @GetMapping
    fun obterFormasPagamento(
            @NotNull @Positive @RequestParam(value = "idUsuario", required = true) idUsuario: Long,
            @NotNull @Positive @RequestParam(value = "idRestaurante", required = true) idRestaurante: Long
    ) : Set<FormasPagamento> {

        val (usuario, restaurante) = validarEntrada(idUsuario, idRestaurante)
        return usuario.filtraFormasPagamento(restaurante)
    }

    private fun validarEntrada(idUsuario: Long, idRestaurante: Long): Pair<Usuario, Restaurante> {
        val usuario = em.find(Usuario::class.java, idUsuario) ?: throw IllegalArgumentException("Usuário não encontrado")
        val restaurante = em.find(Restaurante::class.java, idRestaurante)
                ?: throw IllegalArgumentException("Restaurante não encontrado")
        return Pair(usuario, restaurante)
    }
}
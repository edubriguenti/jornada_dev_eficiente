package com.jornadadev.yfood.pagamentooffline

import com.jornadadev.yfood.entities.Restaurante
import com.jornadadev.yfood.entities.Usuario
import com.jornadadev.yfood.listarformaspagamento.regrasfraude.RegraFraude
import org.springframework.stereotype.Component
import org.springframework.validation.Errors
import org.springframework.validation.Validator
import javax.persistence.EntityManager

@Component
class CombinacaoUsuarioRestauranteFormaPagamentoValidator(
        val em: EntityManager,
        val regrasFraude: Set<RegraFraude>
) : Validator {
    override fun supports(clazz: Class<*>): Boolean =
            clazz.isAssignableFrom(NovoPedidoOfflineRequest::class.java)


    override fun validate(target: Any, errors: Errors) {
        if (errors.hasErrors()) return

        val request = target as NovoPedidoOfflineRequest
        val usuario = em.find(Usuario::class.java, request.idUsuario)
        val restaurante = em.find(Restaurante::class.java, request.idRestaurante)

        val podePagar = usuario.podePagar(restaurante, request.formaPagamento, regrasFraude)
        if (!podePagar) {
            errors.rejectValue(
                    "formaPagamento",
                    "",
                    "A forma de pagamento escolhida é inválida para o restaurante e usuário."
            )
        }

    }

}

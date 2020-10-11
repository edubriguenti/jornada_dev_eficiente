package com.jornadadev.yfood.pagamentooffline

import com.jornadadev.yfood.entities.Restaurante
import com.jornadadev.yfood.entities.Usuario
import com.jornadadev.yfood.listarformaspagamento.regrasfraude.RegraFraude
import com.jornadadev.yfood.pagamentoonline.TemCombinacaoUsuarioRestauranteFormaPagamento
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
            clazz.isAssignableFrom(TemCombinacaoUsuarioRestauranteFormaPagamento::class.java)


    override fun validate(target: Any, errors: Errors) {
        if (errors.hasErrors()) return

        val request = target as TemCombinacaoUsuarioRestauranteFormaPagamento
        val usuario = em.find(Usuario::class.java, request.getIdUsuario())
        val restaurante = em.find(Restaurante::class.java, request.getIdRestaurante())

        val podePagar = usuario.podePagar(restaurante, request.getFormaPagamento(), regrasFraude)
        if (!podePagar) {
            errors.rejectValue(
                    "formaPagamento",
                    "",
                    "A forma de pagamento escolhida é inválida para o restaurante e usuário."
            )
        }

    }

}

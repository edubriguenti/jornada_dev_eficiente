package com.jornadadev.yfood.pagamentooffline

import org.springframework.stereotype.Component
import org.springframework.validation.Errors
import org.springframework.validation.Validator

@Component
class NovoPedidoOfflineRequestValidator (
        val combinacaoValidator: CombinacaoUsuarioRestauranteFormaPagamentoValidator,
        val pagamentoGeradoValidator: PagamentoGeradoValidator

        ) : Validator {
    override fun supports(clazz: Class<*>): Boolean {
        return clazz.isAssignableFrom(NovoPedidoOfflineRequest::class.java)
    }

    override fun validate(target: Any, errors: Errors) {
        if (errors.hasErrors()) return
        combinacaoValidator.validate(target, errors)
        FormaPagamentoOfflineValidator().validate(target, errors)
        pagamentoGeradoValidator.validate(target, errors)
    }

}

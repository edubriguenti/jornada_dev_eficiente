package com.jornadadev.yfood.pagamentoonline

import org.springframework.validation.Errors
import org.springframework.validation.Validator

class FormaPagamentoOnlineValidator : Validator {
    override fun supports(clazz: Class<*>): Boolean =
            clazz.isAssignableFrom(NovoPagamentoOnlineRequest::class.java)

    override fun validate(target: Any, errors: Errors) {
        if (errors.hasErrors()) return
        val request = target as NovoPagamentoOnlineRequest
        if (request.pagamentoOnline()) {
            errors.rejectValue(
                    "formaPagamento",
                    "",
                    "A forma de pagamento deve ser online."
            )
        }
    }

}

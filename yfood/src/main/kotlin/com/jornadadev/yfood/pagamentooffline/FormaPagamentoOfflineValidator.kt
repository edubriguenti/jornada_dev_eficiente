package com.jornadadev.yfood.pagamentooffline

import org.springframework.validation.Errors
import org.springframework.validation.Validator

class FormaPagamentoOfflineValidator : Validator {
    override fun supports(clazz: Class<*>): Boolean =
            clazz.isAssignableFrom(NovoPedidoOfflineRequest::class.java)

    override fun validate(target: Any, errors: Errors) {
        if (errors.hasErrors()) return
        val request = target as NovoPedidoOfflineRequest
        if (!request.isOffline()) {
            errors.rejectValue(
                    "formaPagamento",
                    "",
                    "A forma de pagamento deve ser offline"
            )
        }
    }

}

package com.jornadadev.yfood.pagamentooffline

import com.jornadadev.yfood.repository.PagamentoRepository
import org.springframework.stereotype.Component
import org.springframework.util.Assert
import org.springframework.util.StringUtils
import org.springframework.validation.Errors
import org.springframework.validation.Validator
import org.springframework.web.servlet.HandlerMapping
import javax.annotation.Resource
import javax.persistence.EntityManager
import javax.servlet.http.HttpServletRequest

@Component
class PagamentoGeradoValidator(
        val em: EntityManager,
        val pagamentoRepository: PagamentoRepository
) : Validator {

    @Resource
    lateinit var request: HttpServletRequest

    override fun supports(clazz: Class<*>): Boolean =
            clazz.isAssignableFrom(NovoPedidoOfflineRequest::class.java)

    override fun validate(target: Any, errors: Errors) {
        if (errors.hasErrors()) return

        val variaveisURL = request.
        getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE) as Map<String, String>

        val idPedidoString = variaveisURL["idPedido"]
        Assert.state(StringUtils.hasText(idPedidoString), "Para este validador é necessário que o idPedido esteja preenchido.")
        val idPedido = idPedidoString?.toLong()
        val possivelPagamento = pagamentoRepository.findByIdPedido(idPedido!!)
        if (possivelPagamento.isPresent) {
            errors.rejectValue(null, "", "Já existe pagamento iniciado para este Pedido.")
        }

    }

}

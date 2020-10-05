package com.jornadadev.yfood.pagamentooffline

import com.jornadadev.yfood.entities.FormasPagamentoEnum
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*
import org.springframework.validation.Errors

internal class FormaPagamentoOfflineValidatorTest {

    @Test
    fun `se pedido não é offline deve ser rejeitado`() {
        val validator = FormaPagamentoOfflineValidator()
        val novoPedidoOfflineRequest = NovoPedidoOfflineRequest(FormasPagamentoEnum.ELO, 1, 1)
        val errors = mock(Errors::class.java)
        validator.validate(novoPedidoOfflineRequest, errors)
        verify(errors).rejectValue(
                "formaPagamento", "", "A forma de pagamento deve ser offline")
    }

    @Test
    fun `se pedido é offline não é rejeitado`() {
        val validator = FormaPagamentoOfflineValidator()
        val novoPedidoOfflineRequest = NovoPedidoOfflineRequest(FormasPagamentoEnum.DINHEIRO, 1, 1)
        val errors = mock(Errors::class.java)
        validator.validate(novoPedidoOfflineRequest, errors)
        verify(errors, never()).rejectValue(
                "formaPagamento", "", "A forma de pagamento deve ser offline")
    }

}
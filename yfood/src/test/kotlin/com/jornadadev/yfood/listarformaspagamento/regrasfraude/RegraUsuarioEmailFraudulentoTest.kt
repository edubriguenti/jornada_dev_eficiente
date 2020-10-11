package com.jornadadev.yfood.listarformaspagamento.regrasfraude

import com.jornadadev.yfood.entities.FormaPagamentoEnum
import com.jornadadev.yfood.entities.Usuario
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

internal class RegraUsuarioEmailFraudulentoTest {

    val regra = RegraUsuarioEmailFraudulento()

    @Test
    fun `Deve não aceitar pagamento online para e-mail fraudulento`() {
        //Arrange
        val usuarioFraudulento = Usuario(
                email= "fraudador1@gmail.com",
                formaPagamentoEnum = setOf(FormaPagamentoEnum.VISA, FormaPagamentoEnum.DINHEIRO)
        );

        //ACT
        val aceita = regra.aceita(usuario = usuarioFraudulento, formaPagamento = FormaPagamentoEnum.VISA)

        //ASSERT
        assertFalse(aceita)
    }

    @Test
    fun `Deve aceitar pagamento offline para e-mail fraudulento`() {
        //Arrange
        val usuarioFraudulento = Usuario(
                email= "fraudador1@gmail.com",
                formaPagamentoEnum = setOf(FormaPagamentoEnum.VISA, FormaPagamentoEnum.DINHEIRO)
        );

        //ACT
        val aceita = regra.aceita(usuario = usuarioFraudulento, formaPagamento = FormaPagamentoEnum.DINHEIRO)

        //ASSERT
        assertTrue(aceita)
    }

}

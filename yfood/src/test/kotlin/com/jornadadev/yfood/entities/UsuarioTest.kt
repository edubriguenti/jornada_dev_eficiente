package com.jornadadev.yfood.entities

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

internal class UsuarioTest {

    val seya = Usuario(
            email = "seya@gmail.com",
            formasPagamento = setOf(FormasPagamento.VISA, FormasPagamento.MASTERCARD)
    )

    val sagaSantuario = Restaurante(
            nome = "Saga Santuario",
            formasPagamento = setOf<FormasPagamento>(FormasPagamento.MAQUINA)
    )

    val sagaGuerreirosDeuses = Restaurante(
            nome = "Saga Guerreiros Deuses",
            formasPagamento = setOf<FormasPagamento>(FormasPagamento.VISA, FormasPagamento.MASTERCARD, FormasPagamento.ELO)
    )

    @Test
    fun `Deve retornar os pagamentos aceitos`() {
        //ACT
        val formasPagamentos = seya.filtraFormasPagamento(sagaGuerreirosDeuses)

        //ASSERT
        assertEquals(2, formasPagamentos.size)
        assertTrue(formasPagamentos.containsAll(listOf(FormasPagamento.VISA, FormasPagamento.MASTERCARD)))
    }

    @Test
    fun `Deve retornar lista vazia`() {
        //ACT
        val formasPagamentos = seya.filtraFormasPagamento(sagaSantuario)

        //ASSERT
        assertEquals(0, formasPagamentos.size)
    }
}
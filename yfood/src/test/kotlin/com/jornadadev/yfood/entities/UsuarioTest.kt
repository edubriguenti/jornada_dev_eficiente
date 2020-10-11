package com.jornadadev.yfood.entities

import com.jornadadev.yfood.listarformaspagamento.regrasfraude.RegraFraude
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.hasSize
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito

//@ExtendWith(MockitoExtension::class)
internal class UsuarioTest {

    private fun <T> anyObject(): T {
        return Mockito.anyObject<T>()
    }

    val seya = Usuario(
            email = "seya@gmail.com",
            formaPagamentoEnum = setOf(FormaPagamentoEnum.VISA, FormaPagamentoEnum.MASTERCARD)
    )

    val sagaSantuario = Restaurante(
            nome = "Saga Santuario",
            formaPagamentoEnum = setOf(FormaPagamentoEnum.MAQUINA)
    )

    val sagaGuerreirosDeuses = Restaurante(
            nome = "Saga Guerreiros Deuses",
            formaPagamentoEnum = setOf(FormaPagamentoEnum.VISA, FormaPagamentoEnum.MASTERCARD, FormaPagamentoEnum.ELO)
    )

//    val regraFiltro = Mockito.mock(RegraFraude::class.java)
//    val regrasFiltro = setOf(regraFiltro)

    val regraAceita = object : RegraFraude {
        override fun aceita(usuario: Usuario, formaPagamento: FormaPagamentoEnum): Boolean {
            return true
        }
    }

    val regrasFiltro = setOf(regraAceita)

    @BeforeEach
    fun setup(){
        //Mockito.`when`(regraFiltro.aceita(anyObject(), anyObject())).thenReturn(true)
    }

    @Test
    fun `Deve retornar os pagamentos aceitos`() {
        //ACT
        val formasPagamentos = seya.filtraFormasPagamento(sagaGuerreirosDeuses, regrasFiltro)

        //ASSERT
        assertEquals(2, formasPagamentos.size)
        assertTrue(formasPagamentos.containsAll(listOf(FormaPagamentoEnum.VISA, FormaPagamentoEnum.MASTERCARD)))
    }

    @Test
    fun `Deve retornar lista vazia`() {
        //ACT
        val formasPagamentos = seya.filtraFormasPagamento(sagaSantuario, regrasFiltro)

        //ASSERT
        assertEquals(0, formasPagamentos.size)
    }

    @Test
    fun `Deve retornar vazio se a regra de fraude retornar falso`() {
        val regraFalsa = object : RegraFraude {
            override fun aceita(usuario: Usuario, formaPagamento: FormaPagamentoEnum): Boolean {
                return false
            }
        }

        //ACT
        val formasPagamentos = seya.filtraFormasPagamento(sagaGuerreirosDeuses, setOf(regraFalsa))

        //ASSERT
        assertThat(formasPagamentos, hasSize(0))
    }
}
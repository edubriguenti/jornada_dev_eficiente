package com.jornadadev.yfood.conclusaocompraoffline

import com.jornadadev.yfood.entities.*
import com.jornadadev.yfood.repository.PagamentoRepository
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.springframework.http.HttpStatus
import java.math.BigDecimal
import java.util.*

internal class ConcluiCompraOfflineConstrollerTest {

    val pagamentoRepository = Mockito.mock(PagamentoRepository::class.java)!!
    val controller = ConcluiCompraOfflineConstroller(pagamentoRepository)
    lateinit var pagamento : Pagamento

    @BeforeEach
    fun setup(){
        pagamento = Pagamento(
                idPedido = 1L,
                valor = BigDecimal.TEN,
                usuario = Usuario("email@email.com", setOf(FormasPagamentoEnum.DINHEIRO)),
                restaurante = Restaurante("Restaurante 1", setOf(FormasPagamentoEnum.DINHEIRO)),
                statusTransacao = StatusTransacao.ESPERANDO_CONFIRMACAO_PAGAMENTO
        )
    }


    @Test
    fun `deve retornar 404 para pagamento que não existe`(){
        `when`(pagamentoRepository.findByCodigo("123456"))
                .thenReturn(Optional.empty())
        val response = controller.concluiPagamentoOffline("123456")
        Assertions.assertTrue(response.statusCode == HttpStatus.NOT_FOUND)
    }

    @Test
    fun `deve retornar 400 para pagamento que já concluído`(){
        pagamento.conclui()
        `when`(pagamentoRepository.findByCodigo("123456"))
                .thenReturn(Optional.of(pagamento))
        val response = controller.concluiPagamentoOffline("123456")
        Assertions.assertTrue(response.statusCode == HttpStatus.BAD_REQUEST)
    }

    @Test
    fun `deve concluir um pagamento com sucesso`(){
        val pagamentoObservavel = spy(pagamento)
        `when`(pagamentoRepository.findByCodigo("123456"))
                .thenReturn(Optional.of(pagamentoObservavel))
        val response = controller.concluiPagamentoOffline("123456")
        Assertions.assertTrue(response.statusCode == HttpStatus.OK)
    }

    @AfterEach
    fun clean(){
        reset(pagamentoRepository)
    }

}
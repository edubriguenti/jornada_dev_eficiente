package com.jornadadev.yfood.pagamentooffline

import com.jornadadev.yfood.entities.FormaPagamentoEnum
import com.jornadadev.yfood.entities.Restaurante
import com.jornadadev.yfood.entities.Usuario
import com.jornadadev.yfood.listarformaspagamento.regrasfraude.RegraFraude
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*
import org.springframework.validation.Errors
import javax.persistence.EntityManager

internal class CombinacaoUsuarioRestauranteFormaPagamentoValidatorTest {


    @Test
    fun `verifica se a combinacao usuario, restaurante e formaPagamento é válida` () {
        val em = mock(EntityManager::class.java)
        val regraFraude = object : RegraFraude {
            override fun aceita(usuario: Usuario, formaPagamento: FormaPagamentoEnum): Boolean {
              return true
            }
        }
        val regras = setOf<RegraFraude>(regraFraude)
        val usuarioDinheiro = Usuario("edu@edu.com", setOf(FormaPagamentoEnum.DINHEIRO))
        val restauranteDinheiro = Restaurante("restaurante@edu.com", setOf(FormaPagamentoEnum.DINHEIRO))
        val request = NovoPedidoOfflineRequest(FormaPagamentoEnum.DINHEIRO, 1, 1)
        val errors = mock(Errors::class.java)
        `when`(em.find(Usuario::class.java, 1L)).thenReturn(usuarioDinheiro)
        `when`(em.find(Restaurante::class.java, 1L)).thenReturn(restauranteDinheiro)
        `when`(errors.hasErrors()).thenReturn(false)

        val validator = CombinacaoUsuarioRestauranteFormaPagamentoValidator(em, regras)

        validator.validate(request, errors)

        verify(errors, never()).rejectValue(anyString(), anyString(), anyString())
    }

    @Test
    fun `verifica se a combinacao usuario, restaurante e formaPagamento não é válida` () {
        val em = mock(EntityManager::class.java)
        val regraFraude = object : RegraFraude {
            override fun aceita(usuario: Usuario, formaPagamento: FormaPagamentoEnum): Boolean {
                return true
            }
        }
        val regras = setOf<RegraFraude>(regraFraude)
        val usuarioVISA = Usuario("edu@edu.com", setOf(FormaPagamentoEnum.VISA))
        val restauranteDinheiro = Restaurante("restaurante@edu.com", setOf(FormaPagamentoEnum.DINHEIRO))
        val request = NovoPedidoOfflineRequest(FormaPagamentoEnum.DINHEIRO, 1, 1)
        val errors = mock(Errors::class.java)
        `when`(em.find(Usuario::class.java, 1L)).thenReturn(usuarioVISA)
        `when`(em.find(Restaurante::class.java, 1L)).thenReturn(restauranteDinheiro)
        `when`(errors.hasErrors()).thenReturn(false)

        val validator = CombinacaoUsuarioRestauranteFormaPagamentoValidator(em, regras)

        validator.validate(request, errors)

        verify(errors).rejectValue(anyString(), anyString(), anyString())
    }

}